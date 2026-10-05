package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var names: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = savedInstanceState?.getStringArrayList("names")
            ?: mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val nameAdapter = CustomAdapter(names, this)

        fun updateSelection() {
            nameTextView.text = names.getOrNull(spinner.selectedItemPosition)
                ?: getString(R.string.no_names_remaining)
            spinner.isEnabled = names.isNotEmpty()
            deleteButton.isEnabled = names.isNotEmpty()
        }

        with (spinner) {
            adapter = nameAdapter
            onItemSelectedListener = object: OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    updateSelection()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    updateSelection()
                }
            }
        }

        deleteButton.setOnClickListener {
            val position = spinner.selectedItemPosition
            if (position !in names.indices) return@setOnClickListener

            names.removeAt(position)
            nameAdapter.notifyDataSetChanged()
            if (names.isNotEmpty()) {
                spinner.setSelection(position.coerceAtMost(names.lastIndex))
            }
            // The selection callback need not run when the position stays the same.
            updateSelection()
        }
        updateSelection()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList("names", ArrayList(names))
        super.onSaveInstanceState(outState)
    }
}
