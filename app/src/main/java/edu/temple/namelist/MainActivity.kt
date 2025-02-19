package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var names: MutableList<String>
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        adapter = CustomAdapter(names, this@MainActivity)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nameTextView.text = names[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            if (names.isNotEmpty()) {
                val selectedPosition = spinner.selectedItemPosition
                names.removeAt(selectedPosition)
                adapter.notifyDataSetChanged()

                if (names.isNotEmpty()) {
                    spinner.setSelection(0)
                    nameTextView.text = names[0]
                } else {
                    nameTextView.text = "Empty"
                }
            }
        }
    }
}