package edu.temple.namelist

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var names: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf(
            "Kevin Shaply",
            "Stacey Lou",
            "Gerard Clear",
            "Michael Studdard",
            "Michelle Studdard"
        )

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        val adapter = CustomAdapter(names, this)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position in names.indices) {
                    nameTextView.text = names[position]
                    deleteButton.isEnabled = true
                } else {
                    nameTextView.text = ""
                    deleteButton.isEnabled = false
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                nameTextView.text = ""
                deleteButton.isEnabled = names.isNotEmpty()
            }
        }

        deleteButton.setOnClickListener {
            val pos = spinner.selectedItemPosition
            if (pos != AdapterView.INVALID_POSITION && pos in names.indices) {
                names.removeAt(pos)
                adapter.notifyDataSetChanged()

                if (names.isNotEmpty()) {
                    // Choose a valid selection (same index if possible, otherwise last)
                    val newPos = pos.coerceAtMost(names.lastIndex)
                    spinner.setSelection(newPos)
                    nameTextView.text = names[newPos]
                } else {
                    nameTextView.text = ""
                    deleteButton.isEnabled = false
                }
            }
        }

        // Initial UI state
        deleteButton.isEnabled = names.isNotEmpty()
        if (names.isNotEmpty()) {
            spinner.setSelection(0)
            nameTextView.text = names[0]
        } else {
            nameTextView.text = ""
        }
    }
}
