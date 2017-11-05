package com.rousci.androidapp.widgetnote.viewPresenter.setting

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.TypedValue
import android.view.MenuItem
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresenter.*
import com.rousci.androidapp.widgetnote.viewPresenter.widget.NoteWidget

/**
 * Created by rousci on 17-11-2.
 */
var getContext: () -> Setting = {null!!}

fun setPresenter(setting: Setting){
    getContext = {setting}
}

fun finishPR(){
    val frequencyEdited = getContext().frequencyEditor!!.text.toString().toInt()
    val fontEdited = getContext().fontSizeEditor!!.text.toString().toFloat()

    val editor = getContext().getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
    editor.putInt(frequency, frequencyEdited)
    editor.putFloat(fontSP, fontEdited)
    val time = getContext().getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(timeCounter, defaultFrequency)
    if (time >= frequencyEdited){
        editor.putInt(timeCounter, 0)
    }
    editor.apply()

    val context = getContext() as Context
    val appWidgetManager = AppWidgetManager.getInstance(context)
    val views = RemoteViews(context.packageName, R.layout.note_widget)
    val componentName = ComponentName(context, NoteWidget::class.java)
    val lastNote = getContext().getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getString(lastChoicedNote, "没有添加数据")
    views.setTextViewText(R.id.appwidget_text, lastNote)
    views.setTextViewTextSize(R.id.appwidget_text, TypedValue.COMPLEX_UNIT_SP ,fontEdited)
    appWidgetManager.updateAppWidget(componentName, views)
}

fun onOptionsItemSelectedPR(item: MenuItem){
    when(item.itemId){
        android.R.id.home -> {
            getContext().finish()
        }
    }
}