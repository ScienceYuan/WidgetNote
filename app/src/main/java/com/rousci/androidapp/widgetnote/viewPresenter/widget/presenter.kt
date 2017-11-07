package com.rousci.androidapp.widgetnote.viewPresenter.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.TypedValue
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresenter.*
import java.util.*

/**
 * Created by rousci on 17-10-24.
 *
 * control the widget
 */

fun updateWidgetOnTime(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray, noteList: List<String>){

    val frequency = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(frequency, defaultFrequency)
    val time = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(timeCounter, 0)
    val editor = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()

    if(time + 1 >= frequency){
        updateNoteData(context, noteList)
        editor.putInt(timeCounter, 0)
    }
    else{
        editor.putInt(timeCounter, time + 1)
    }

    updateWidget(context, appWidgetManager, appWidgetIds)
    editor.apply()
}

fun updateWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray){
    val lastNote = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getString(lastChoicedNote, null)

    val views = RemoteViews(context.packageName, R.layout.note_widget)
    val fontSize = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getFloat(fontSP, fontSPDefault)
    views.setTextViewTextSize(R.id.appwidget_text, TypedValue.COMPLEX_UNIT_SP, fontSize)
    views.setTextViewText(R.id.appwidget_text, lastNote)
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetIds, views)
}

fun updateNoteData(context: Context, noteList: List<String>){
    val lastNote = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getString(lastChoicedNote, null)
    val notes = if(noteList.lastIndex >= 1){
        noteList.filter { it != lastNote }
    }
    else {
        noteList
    }

    val randomNote = randomChoice(notes)
    val editor = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
    editor.putString(lastChoicedNote, randomNote)
    editor.apply()
}

fun randomChoice(stringList:List<String>): String {
    val lastIndex = stringList.lastIndex
    val random = Random()
    val randomPositive = Math.abs(random.nextInt())
    val choiceIndex = randomPositive % (lastIndex + 1)
    val randomString = stringList[choiceIndex]

    return randomString
}