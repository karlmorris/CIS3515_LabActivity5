package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var names: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        val adapter = CustomAdapter(names, this)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0 && position < names.size) {
                    nameTextView.text = names[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                nameTextView.text = ""
            }
        }

        deleteButton.setOnClickListener {
            val position = spinner.selectedItemPosition
            if (position != AdapterView.INVALID_POSITION) {
                val deletedName = names[position]
                names.removeAt(position)
                adapter.notifyDataSetChanged()

                if (nameTextView.text == deletedName) {
                    if (names.isNotEmpty()) {
                        nameTextView.text = names[if (position < names.size) position else names.size - 1] // Set to the next item or the last one.
                    } else {
                        nameTextView.text = ""
                    }
                }
            }
        }
    }
}
