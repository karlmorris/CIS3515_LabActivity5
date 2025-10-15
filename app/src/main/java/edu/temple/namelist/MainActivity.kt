package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.BaseAdapter
import android.widget.Spinner
import android.widget.TextView
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    lateinit var names: MutableList<String>

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

        with(spinner) {
            adapter = CustomAdapter(names, this@MainActivity)
            onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    parent?.run {
                        nameTextView.text = getItemAtPosition(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        findViewById<View>(R.id.deleteButton).setOnClickListener {
            if (names.isNotEmpty()) {
                val pos = spinner.selectedItemPosition
                names.removeAt(pos)
                (spinner.adapter as BaseAdapter).notifyDataSetChanged()

                if (names.isNotEmpty()) {
                    spinner.setSelection(min(pos, names.lastIndex))
                } else {
                    nameTextView.text = ""
                }
            }
        }
    }
}
