package edu.temple.namelist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(
    private val names: MutableList<String>,
    private val context: Context
) : BaseAdapter() {

    override fun getCount(): Int = names.size

    override fun getItem(position: Int): Any = names[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tv = (convertView as? TextView) ?: TextView(context).apply {
            textSize = 24f
            setPadding(16, 16, 16, 16)
        }
        tv.text = names[position]
        return tv
    }

    // Provide a dropdown view so Spinner renders the list nicely too
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tv = (convertView as? TextView) ?: TextView(context).apply {
            textSize = 18f
            setPadding(24, 24, 24, 24)
        }
        tv.text = names[position]
        return tv
    }
}
