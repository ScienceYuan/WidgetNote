package com.rousci.androidapp.widgetnote.viewPresenter.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import com.rousci.androidapp.widgetnote.BuildConfig
import com.rousci.androidapp.widgetnote.viewPresenter.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

/**
 * Created by rousci on 17-11-5.
 * test without dagger
 * because I haven't learned that
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,buildDir = "app/build")
class Presenter {
    val data = listOf("one", "two", "three", "four")
    val context = mock(Context::class.java)
    val sharedPreferences = mock(SharedPreferences::class.java)
    val widgetId = intArrayOf(1, 2, 3)
    val widgetManager = mock(AppWidgetManager::class.java)
    val editor = mock(SharedPreferences.Editor::class.java)
    val frequencyTest = 2

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
        Mockito.`when`(sharedPreferences.getInt(eq(frequency), eq(defaultFrequency))).thenReturn(frequencyTest)
        Mockito.`when`(sharedPreferences.getInt(eq(timeCounter), eq(0))).thenReturn(0)

        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)
        Mockito.`when`(editor.putString(eq(lastChoicedNote), ArgumentMatchers.anyString())).thenReturn(editor)
        Mockito.`when`(editor.putInt(eq(timeCounter), ArgumentMatchers.anyInt())).thenReturn(editor)
        doNothing().`when`(editor).apply()
    }

    @Test
    fun randomChoiceNorman(){
        val random = randomChoice(data)
        val randomIsIn = random in data
        assertEquals(randomIsIn, true)
    }

    @Test(expected = ArithmeticException::class)
    fun choiceFromEmpty(){
        val emptyData = emptyList<String>()
        val choiced = randomChoice(emptyData)
        assert(choiced in emptyData)
    }

    @Test
    fun updateNoteDataNorman(){
        updateNoteData(context, data)
        verify(context, times(2)).getSharedPreferences(eq(singleDataPreference), eq(Context.MODE_PRIVATE))
        verify(editor).putString(eq(lastChoicedNote), ArgumentMatchers.anyString())
    }

    @Test(expected = ArithmeticException::class)
    fun updateNoteDataEmpty(){
        updateNoteData(context, emptyList())
        verify(context, times(2)).getSharedPreferences(eq(singleDataPreference), eq(Context.MODE_PRIVATE))
        verify(editor).putString(eq(lastChoicedNote), ArgumentMatchers.anyString())
    }

    @Test
    fun updateWidgetNorman(){
        updateWidget(context, widgetManager, widgetId)
        verify(sharedPreferences).getString(eq(lastChoicedNote), ArgumentMatchers.any())
        verify(sharedPreferences).getFloat(eq(fontSP), eq(fontSPDefault))
        verify(widgetManager).updateAppWidget(eq(widgetId), ArgumentMatchers.any())
    }

    @Test
    fun updateWidgetOnTimeNorman(){
        updateWidgetOnTime(context, widgetManager, widgetId, data)
        verify(sharedPreferences).getInt(eq(frequency), eq(defaultFrequency))
        verify(sharedPreferences).getFloat(eq(fontSP), eq(fontSPDefault))
        verify(sharedPreferences).getInt(eq(timeCounter), ArgumentMatchers.anyInt())
        verify(editor).putInt(eq(timeCounter), ArgumentMatchers.anyInt())
        verify(editor).apply()
    }

    @Test
    fun updateWidgetOnTimeUp(){
        Mockito.`when`(sharedPreferences.getInt(eq(frequency), eq(Context.MODE_PRIVATE))).
                thenReturn(2)
        Mockito.`when`(sharedPreferences.getInt(eq(timeCounter), eq(Context.MODE_PRIVATE))).
                thenReturn(1)

        updateWidgetOnTime(context, widgetManager, widgetId, data)
        verify(sharedPreferences).getInt(eq(frequency), eq(defaultFrequency))
        verify(sharedPreferences).getFloat(eq(fontSP), eq(fontSPDefault))
        verify(sharedPreferences).getInt(eq(timeCounter), ArgumentMatchers.anyInt())
        verify(editor, times(2)).apply()

        verify(editor).putInt(eq(timeCounter), eq(0))
    }
}