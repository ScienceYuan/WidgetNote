package com.rousci.androidapp.widgetnote.viewPresenter.widget

import android.app.WallpaperManager
import android.appwidget.AppWidgetManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
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
    Log.d("track", "widget update")
    val lastNote = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getString(lastChoicedNote, null)

    val views = RemoteViews(context.packageName, R.layout.note_widget)
    val fontSize = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getFloat(fontSP, fontSPDefault)
    Log.d("font size", fontSize.toString())
    views.setTextViewTextSize(R.id.appwidget_text, TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
    views.setTextViewText(R.id.appwidget_text, lastNote)
    val isBlackWallpaper = isBlack(getWallpaper(context))
    if (!isBlackWallpaper){
        views.setTextColor(R.id.appwidget_text, android.graphics.Color.BLACK)
    }
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

fun isBlack(bitmap: Bitmap): Boolean {
    fun isBlack(color: Int): Boolean{
        val R = (color shr 16) and 0xff
        val G = (color shr 8) and 0xff
        val B = color and 0xff
        val level = R * 0.299 + G * 0.587 + B * 0.114
        return level < 192
    }


    val rowIndex = (0..7).toList().map { bitmap.width / 8 * it}
    val columnIndex = (0..7).toList().map { bitmap.height / 8 * it }
    val points = rowIndex
            .map { a -> columnIndex
                    .map {b -> a to b } }
            .reduce { acc, list -> acc + list }
    val pixelColors = points
            .map { bitmap.getPixel(it.first, it.second) }
            .map { isBlack(it) }
    return pixelColors.count {it == true} > pixelColors.count{it == false}
}

fun getWallpaper(context: Context): Bitmap {
    val wallpaperManager = WallpaperManager.getInstance(context)
    return (wallpaperManager.drawable as BitmapDrawable).bitmap
}
