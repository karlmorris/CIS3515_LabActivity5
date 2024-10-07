package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var names: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        with(spinner) {
            adapter = CustomAdapter(names, this@MainActivity)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // Check if the names list is not empty and the position is valid
                    if (names.isNotEmpty() && position in names.indices) {
                        // Set the TextView to the selected name
                        nameTextView.text = names[position]
                    } else {
                        // Clear the TextView if there are no names
                        nameTextView.text = ""
                    }
                }




                override fun onNothingSelected(p0: AdapterView<*>?) {
                    nameTextView.text = ""
                }
            }
        }

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            (names as MutableList).removeAt(spinner.selectedItemPosition)
            (spinner.adapter as BaseAdapter).notifyDataSetChanged()
        }

    }
}