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

    val names: MutableList<String> = mutableListOf(
        "Kevin Shaply",
        "Stacey Lou",
        "Gerard Clear",
        "Michael Studdard",
        "Michelle Studdard"
    )
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        adapter = CustomAdapter(names, this)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p0?.run {
                    nameTextView.text = getItemAtPosition(p2).toString()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


        findViewById<View>(R.id.deleteButton).setOnClickListener{
            val selectedIndex = spinner.selectedItemPosition
            if (selectedIndex != AdapterView.INVALID_POSITION) {
                names.removeAt(selectedIndex)
                adapter.notifyDataSetChanged()
            }
        }
    }
}