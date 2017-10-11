package com.rousci.androidapp.widgetnote.view.mainActicity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rousci.androidapp.widgetnote.R

/**
 * Created by rousci on 17-10-11.
 * customize a adapter
 */
class StringRecycleAdapter(val data:List<String>, val context: Context): RecyclerView.Adapter<StringRecycleAdapter.StrViewHolder>() {
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StrViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StrViewHolder {
        val view:View = View.inflate(context, R.layout.item, null)
        return StrViewHolder(view)
    }

    class StrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView1)
    }
}