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
import android.widget.Toast
import androidx.core.view.isInvisible

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
            //performing another check before removing an element from the array
            if(names.isNotEmpty()) {
                (names as MutableList).removeAt(spinner.selectedItemPosition)
                (spinner.adapter as BaseAdapter).notifyDataSetChanged()
            }
            if(!spinner.adapter.isEmpty) {
                //the crash stems from accessing the name at position 0
                //and there is nothing at position 0
                nameTextView.text = names[0]
            }else{
                it.isEnabled = false //disables the delete button
                spinner.isInvisible = true //hides the spinner if the list is empty
                nameTextView.text = "no more names in the list"
            }
        }

    }
}