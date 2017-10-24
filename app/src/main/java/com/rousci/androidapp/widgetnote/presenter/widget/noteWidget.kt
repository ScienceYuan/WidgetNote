package com.rousci.androidapp.widgetnote.presenter.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
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
    val notes = queryAll()
    val lastIndex = notes.lastIndex

    for (id in appWidgetIds!!){

        val views = RemoteViews(context!!.packageName, R.layout.note_widget)
        val random = Random()
        val timer = Timer()

        fun setText(text: String){
            views.setTextViewText(R.id.appwidget_text, text)
        }

        val timerTasker = object: TimerTask(){
            override fun run() {
                val randomNum = random.nextInt(lastIndex + 1)
                setText(notes[randomNum])
                appWidgetManager!!.updateAppWidget(id, views)
                Log.d("test",randomNum.toString())
            }
        }

        setText("begin")
        timer.schedule(timerTasker, 0, 5000)


        // Instruct the widget manager to update the widget
        appWidgetManager!!.updateAppWidget(id, views)
    }
}