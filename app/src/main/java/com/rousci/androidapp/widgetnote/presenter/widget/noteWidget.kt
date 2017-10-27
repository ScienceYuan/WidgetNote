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
    val lastChoicedId = context.getSharedPreferences(lastNoteIdPre, 0).getInt("lastChoicedId", -1)
    val notes = queryAll()
    val lastIndex = notes.lastIndex


    if(lastIndex != -1){

        fun iter2List(iter: Iterable<Int>): List<Int> {
            val result = mutableListOf<Int>()
            result.addAll(iter)
            return result.toList()
        }

        val views = RemoteViews(context.packageName, R.layout.note_widget)

        val indexList = iter2List(0..lastIndex)
        indexList.filterIndexed {index, intRange -> index != lastChoicedId}
        val numOfIndex = indexList.lastIndex + 1
        val random = Random()
        val randomPositive = Math.abs(random.nextInt())
        val choicedIndex = randomPositive % (numOfIndex)

        val randomIndex = indexList[choicedIndex]

        val editor = context.getSharedPreferences(lastNoteIdPre, 0).edit()
        editor.putInt("lastChoicedId", randomIndex)
        editor.commit()

        for (id in appWidgetIds!!){

            views.setTextViewText(R.id.appwidget_text, notes[randomIndex])
            // Instruct the widget manager to update the widget
            appWidgetManager!!.updateAppWidget(id, views)
        }
    }
}