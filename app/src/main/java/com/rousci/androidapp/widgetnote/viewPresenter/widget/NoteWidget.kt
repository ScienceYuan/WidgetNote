package com.rousci.androidapp.widgetnote.viewPresenter.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.Toast
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Implementation of App Widget functionality.
 */
class NoteWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        try {
            updateWidgetOnTime(context, appWidgetManager, appWidgetIds, context.database.use {
                select(noteTableName, idName, contentName)
                        .parseList(classParser<Note>())
                        .map { it.content }
            })
        }
        catch (e: ArithmeticException){
            Toast.makeText(context, R.string.empty_data, Toast.LENGTH_SHORT).show()
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context) {

    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}