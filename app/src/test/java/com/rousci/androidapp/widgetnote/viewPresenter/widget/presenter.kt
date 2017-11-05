package com.rousci.androidapp.widgetnote.viewPresenter.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import com.rousci.androidapp.widgetnote.viewPresenter.fontSP
import com.rousci.androidapp.widgetnote.viewPresenter.fontSPDefault
import com.rousci.androidapp.widgetnote.viewPresenter.lastChoicedNote
import com.rousci.androidapp.widgetnote.viewPresenter.singleDataPreference
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import kotlin.test.assertEquals

/**
 * Created by rousci on 17-11-5.
 * test without dagger
 * because I haven't learned that
 */

class TestForPresenter{
    val data = listOf("one", "two", "three", "four")
    val context = mock(Context::class.java)
    val sharedPreferences = mock(SharedPreferences::class.java)
    val widgetId = intArrayOf(1, 2, 3)
    val widgetManager = mock(AppWidgetManager::class.java)

    companion object {
        @BeforeClass
        @JvmStatic
        fun initFullTest(){

        }
    }

    @Before
    fun initSingleTest(){
        Mockito.`when`(context.getSharedPreferences(eq(singleDataPreference), eq(0))).thenReturn(sharedPreferences)
        Mockito.`when`(sharedPreferences.getString(eq(lastChoicedNote), eq(null))).thenReturn("lastNote")
        Mockito.`when`(sharedPreferences.getFloat(eq(fontSP), eq(fontSPDefault))).thenReturn(20.toFloat())
    }

    @Test
    fun testRandomChoice(){
        val random = randomChoice(data)
        val randomIsIn = random in data
        assertEquals(randomIsIn, true)
    }

    @Test
    fun testUpdateWidget(){
        updateWidget(context, widgetManager, widgetId)
        verify(context).getSharedPreferences(singleDataPreference, 0)
    }
}