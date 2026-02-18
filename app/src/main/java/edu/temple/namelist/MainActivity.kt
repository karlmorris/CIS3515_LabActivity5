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
        val adapter = CustomAdapter(names, this@MainActivity)
        spinner.adapter = adapter

        with (spinner) {
            onItemSelectedListener = object: OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    p0?.run {
                        if(p2 >= 0 && p2 < names.size) {
                            nameTextView.text = getItemAtPosition(p2).toString()
                        }
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            val position = spinner.selectedItemPosition
            if(position >= 0 && position < names.size){
                names.removeAt(position)
                adapter.notifyDataSetChanged()

                if(names.isNotEmpty()){
                    spinner.setSelection(kotlin.math.min(position, names.size - 1))
                } else{
                    nameTextView.text = ""
                }
            }
        }

    }
}