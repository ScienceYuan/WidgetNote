package com.rousci.androidapp.widgetnote.viewPresenter.mainActivity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.Note
import com.rousci.androidapp.widgetnote.viewPresenter.noteId
import com.rousci.androidapp.widgetnote.viewPresenter.editNote.EditNote
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

/**
 * Created by rousci on 17-10-11.
 * customize a adapter
 */
class NoteRecycleAdapter(val data:List<Note>, val appContext: Context): RecyclerView.Adapter<NoteRecycleAdapter.StrViewHolder>() {
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StrViewHolder, position: Int) {
        holder.textView.text = data[position].content
        holder.button.onClick {
            appContext.startActivity<EditNote>(noteId to data[position].id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StrViewHolder {
        val view:View = LayoutInflater.from(appContext).inflate(R.layout.note_item, parent, false)
        return StrViewHolder(view)
    }

    class StrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.find<TextView>(R.id.textView1)
        val button = itemView.find<ImageButton>(R.id.button1)
    }
}