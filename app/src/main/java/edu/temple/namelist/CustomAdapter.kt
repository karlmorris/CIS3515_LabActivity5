package edu.temple.namelist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(private var names: MutableList<String>, private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return names.size
    }

    override fun getItem(position: Int): Any {
        return names[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView: TextView = if (convertView != null) {
            convertView as TextView
        } else {
            TextView(context).apply {
                textSize = 24f
                setPadding(10, 10, 10, 10)
            }
        }
        textView.text = getItem(position).toString()
        return textView
    }
}
