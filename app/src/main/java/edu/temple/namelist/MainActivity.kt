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

    lateinit var names: List<String>
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

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            val selectedItem = spinner.selectedItemPosition
            (names as MutableList).removeAt(selectedItem)
            (spinner.adapter as BaseAdapter).notifyDataSetChanged()
            /*
                These lines have been added to automatically update the display text when the
                previously displayed item is deleted. This is because the selection in the spinner
                would change, but not notify the display text to change.
             */
            if(selectedItem<spinner.adapter.count)
                nameTextView.text = spinner.selectedItem.toString()

            /*
                This line has been added to alert the user when there are no names left.
             */
            if(spinner.adapter.count == 0) nameTextView.text = "No names to display. :("
        }

    }
}