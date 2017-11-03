package com.rousci.androidapp.widgetnote.viewPresent.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.TypedValue
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.model.setDatabase
import com.rousci.androidapp.widgetnote.viewPresent.*
import java.util.*

/**
 * Created by rousci on 17-10-24.
 *
 * control the widget
 */

fun updateAppWidgetOnTime(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray){
    val frequency = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(frequency, defaultFrequency)
    val time = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(timeCounter, 0)
    val editor = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
    editor.putInt(timeCounter, time + 1)
    if(time + 1 >= frequency){
        updateAppWidget(context, appWidgetManager, appWidgetIds)
        editor.putInt(timeCounter, 0)
    }
    editor.apply()
}

fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray){

    setDatabase(context)
    val lastNote = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getString(lastChoicedNote, null)
    val notes = queryAll().filter { it != lastNote }
    val lastIndex = notes.lastIndex

    if(lastIndex != -1){
        val views = RemoteViews(context.packageName, R.layout.note_widget)

        val random = Random()
        val randomPositive = Math.abs(random.nextInt())
        val choiceIndex = randomPositive % (lastIndex + 1)

        val randomNote = notes[choiceIndex]

        val editor = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
        editor.putString(lastChoicedNote, randomNote)
        editor.apply()

        for (id in appWidgetIds){
            val fontSize = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getFloat(fontSP, fontSPDefault)
            views.setTextViewTextSize(R.id.appwidget_text, TypedValue.COMPLEX_UNIT_SP, fontSize)
            views.setTextViewText(R.id.appwidget_text, randomNote)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(id, views)
        }
    }
}