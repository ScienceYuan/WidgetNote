package com.rousci.androidapp.widgetnote.presenter.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.lastNoteIdPre
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.model.setDatabase
import java.util.*

/**
 * Created by rousci on 17-10-24.
 *
 * control the widget
 */

fun updateAppWidget(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?){

    setDatabase(context!!)
    val lastNoteId = context!!.getSharedPreferences(lastNoteIdPre, 0).getInt("lastNoteId", -1)
    val notes = queryAll().filterIndexed { index, s -> index != lastNoteId }
    val lastIndex = notes.lastIndex

    if(lastIndex != -1){

        val views = RemoteViews(context!!.packageName, R.layout.note_widget)
        val random = Math.abs(Random().nextInt() % (lastIndex + 1))

        context!!.getSharedPreferences(lastNoteIdPre, 0).edit().putInt("lastNoteId", random)

        for (id in appWidgetIds!!){

            views.setTextViewText(R.id.appwidget_text, notes[random])
            // Instruct the widget manager to update the widget
            appWidgetManager!!.updateAppWidget(id, views)
        }
    }
}