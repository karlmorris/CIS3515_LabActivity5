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

    // Mutable list
    lateinit var names: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        with (spinner) {
            adapter = CustomAdapter(names, this@MainActivity)
            onItemSelectedListener = object: OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    p0?.run {
                        nameTextView.text = getItemAtPosition(p2).toString()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }


        /*

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            val position = spinner.selectedItemPosition
            // since names already is mutable list
            // set condition make sure position is exist in the range of the list
            if (position >= 0 && position < names.size) {
                names.removeAt(position) // Remove the selected name
                (spinner.adapter as CustomAdapter).notifyDataSetChanged() // Notify adapter of data change
            }
        }

         */

        // add: handle if exist empty list
        findViewById<View>(R.id.deleteButton).setOnClickListener {
            val position = spinner.selectedItemPosition
            if (position >= 0 && position < names.size) {
                names.removeAt(position) // Remove the selected name
                (spinner.adapter as CustomAdapter).notifyDataSetChanged() // Notify adapter of data change

                // Check if the names list is empty
                if (names.isEmpty()) {
                    nameTextView.text = "" // Clear the TextView, and make it blank
                    spinner.setSelection(-1) // Reset spinner selection
                    // Optionally disable the spinner or update its state
                    spinner.isEnabled = false // Disable the spinner
                } else {
                    spinner.isEnabled = true // Re-enable the spinner if there are names
                }
            }
        }

    }
}