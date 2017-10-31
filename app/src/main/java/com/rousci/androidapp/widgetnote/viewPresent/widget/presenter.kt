package com.rousci.androidapp.widgetnote.viewPresent.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.model.setDatabase
import com.rousci.androidapp.widgetnote.viewPresent.frequency
import com.rousci.androidapp.widgetnote.viewPresent.lastChoicedNote
import com.rousci.androidapp.widgetnote.viewPresent.singleDataPreference
import java.util.*

/**
 * Created by rousci on 17-10-24.
 *
 * control the widget
 */

fun updateAppWidgetOnTime(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray){
    val frequency = context.getSharedPreferences(frequency, Context.MODE_PRIVATE)
}

fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray){

    setDatabase(context)
    val lastChoicedNote = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getString(lastChoicedNote, null)
    val notes = queryAll().filter { it != lastChoicedNote }
    val lastIndex = notes.lastIndex

    if(lastIndex != -1){
        val views = RemoteViews(context.packageName, R.layout.note_widget)

        val random = Random()
        val randomPositive = Math.abs(random.nextInt())
        val choicedIndex = randomPositive % (lastIndex + 1)

        val randomNote = notes[choicedIndex]

        val editor = context.getSharedPreferences(singleDataPreference, 0).edit()
        editor.putString(com.rousci.androidapp.widgetnote.viewPresent.lastChoicedNote, randomNote)
        editor.commit()

        for (id in appWidgetIds){
            views.setTextViewText(R.id.appwidget_text, randomNote)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(id, views)
        }
    }
}