package com.rousci.androidapp.widgetnote.viewPresenter.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.model.setDatabase

/**
 * Implementation of App Widget functionality.
 */
class NoteWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        setDatabase(context)
        updateWidgetOnTime(context, appWidgetManager, appWidgetIds, queryAll())
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context) {

    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}