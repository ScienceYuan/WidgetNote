package com.rousci.androidapp.widgetnote.viewPresenter.widget

import android.app.WallpaperManager
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import androidx.test.core.app.ApplicationProvider
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresenter.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowBitmap
import org.robolectric.shadows.ShadowWallpaperManager
import kotlin.test.assertEquals

/**
 * Created by rousci on 17-11-5.
 */
@RunWith(RobolectricTestRunner::class)
class Presenter {
    val data = listOf("one", "two", "three", "four")
    val context = mock(Context::class.java)
    val sharedPreferences = mock(SharedPreferences::class.java)
    val widgetId = intArrayOf(1, 2, 3)
    val widgetManager = mock(AppWidgetManager::class.java)
    val editor = mock(SharedPreferences.Editor::class.java)
    val frequencyTest = 2
    val bitmap =  Bitmap.createBitmap(1080, 1920, Bitmap.Config.RGBA_F16)

    @Before
    fun initSingleTest(){
        `when`(context.getSharedPreferences(eq(singleDataPreference), eq(0))).thenReturn(sharedPreferences)

        `when`(sharedPreferences.getString(eq(lastChoicedNote), eq(null))).thenReturn("lastNote")
        `when`(sharedPreferences.getFloat(eq(fontSP), eq(fontSPDefault))).thenReturn(20.toFloat())
        `when`(sharedPreferences.getInt(eq(frequency), eq(defaultFrequency))).thenReturn(frequencyTest)
        `when`(sharedPreferences.getInt(eq(timeCounter), eq(0))).thenReturn(0)

        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(eq(lastChoicedNote), ArgumentMatchers.anyString())).thenReturn(editor)
        `when`(editor.putInt(eq(timeCounter), ArgumentMatchers.anyInt())).thenReturn(editor)

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
}