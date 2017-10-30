package com.rousci.androidapp.widgetnote.viewPresent.mainActicity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresent.notePosition
import com.rousci.androidapp.widgetnote.viewPresent.editNote.EditNote
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

/**
 * Created by rousci on 17-10-11.
 * customize a adapter
 */
class StringRecycleAdapter(val data:List<String>, val appContext: Context): RecyclerView.Adapter<StringRecycleAdapter.StrViewHolder>() {
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StrViewHolder, position: Int) {
        holder.textView.text = data[position]
        holder.button.onClick {
            appContext.startActivity<EditNote>(notePosition to position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StrViewHolder {
        val view:View = LayoutInflater.from(appContext).inflate(R.layout.note_item, parent, false)
        return StrViewHolder(view)
    }

    class StrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView1)
        val button:Button = itemView.findViewById(R.id.button1)
    }
}