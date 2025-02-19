package edu.temple.namelist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(private val names: List<String>, private val context: Context) : BaseAdapter() {

    // How many items are in the collection
    override fun getCount(): Int {
        return names.size
        //this was changed from 5. the size of the array is varable
    }

    // Fetch an item from the collection
    override fun getItem(p0: Int): Any {
        return names[p0]
    }

    // Get the associated ID of an item in the collection
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    // Return a view associated with an item in the collection
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        return if (p1 != null) {
            p1 as TextView//this if statement checks if p1, a View, exists and if it does, it will reuse it
        } else {
            TextView(context).apply {//if not, then a new view is created
                textSize = 24f
                setPadding(10,10,10,10)
                //these sets the defaults of the textview so the app doesnt crash
            }
        }.apply {
            text = getItem(p0).toString()
            //sets the text of the textview that was either reused or newly made to the item selected that is of type int
        }
    }
}