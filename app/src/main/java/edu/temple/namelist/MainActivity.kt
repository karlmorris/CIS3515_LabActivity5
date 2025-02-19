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

        names = mutableListOf(
            "Kevin Shaply",
            "Stacey Lou",
            "Gerard Clear",
            "Michael Studdard",
            "Michelle Studdard"
        )
        //a mutable list is a list that can be modified

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)



        with(spinner) {//every statement following is preceded with 'spinner.'(the val that was declared earlier
            adapter = CustomAdapter(
                names,
                this@MainActivity
            ) //initiates the adapter and passes the array through it
            onItemSelectedListener =
                object : OnItemSelectedListener { // a item selection listener only for the spinner
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long
                    ) { // adds functions to when an item is selected

                            p0?.run { //executes when the selected item in the adapterview(aka a spinner) is not null
                                nameTextView.text =
                                    getItemAtPosition(p2).toString() //converts the item selected at the position of type Int to String.
                            }
                    }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }//nothing occurs when nothing is selected
        }
    }

        findViewById<View>(R.id.deleteButton).setOnClickListener {//initializes the button and adds a onclick listener
            if(names.isNotEmpty()) {//checks if the array is empty
                (names as MutableList).removeAt(spinner.selectedItemPosition)//removes the selected item from the array
                (spinner.adapter as BaseAdapter).notifyDataSetChanged() //updates the adapter with the new array, now that the array has one less item in it
                //you have to pass it as baseadapter because you are trying to use the function notifydatasetchanged.
            }
            if (names.isEmpty()) { //if the array is empty, then display this text when the button for delete is clicked
                nameTextView.text = "No more names."
            } else {//if the array is not empty, auto select the top element in the array so that the old deleted name is not still displayng on the screen
                //chatgpt help me here
                spinner.setSelection(0)
                nameTextView.text = names[0]

            }

        }

    }
}