package com.rousci.androidapp.widgetnote.viewPresenter.setting

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.view.MenuItem
import android.widget.EditText
import com.rousci.androidapp.widgetnote.BuildConfig
import com.rousci.androidapp.widgetnote.viewPresenter.singleDataPreference
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by rousci on 17-11-7.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, buildDir = "app/build")
class Presenter{

    val setting = mock(Setting::class.java)

    val finishItem = mock(MenuItem::class.java)

    val frequencyEditor = mock(EditText::class.java)
    val frequencyText = mock(Editable::class.java)

    val fontEditor = mock(EditText::class.java)
    val fontText = mock(Editable::class.java)

    val sharePreference = mock(SharedPreferences::class.java)
    val editor = mock(SharedPreferences.Editor::class.java)

    val wigdetManager = mock(AppWidgetManager::class.java)

    val testFontSize = 24
    val testFrequence = 2

    @Before
    fun before(){
        Mockito.`when`(finishItem.itemId).thenReturn(android.R.id.home)

        Mockito.`when`(setting.frequencyEditor).thenReturn(frequencyEditor)
        Mockito.`when`(frequencyEditor.text).thenReturn(frequencyText)
        Mockito.`when`(frequencyText.toString()).thenReturn(testFrequence.toString())

        Mockito.`when`(setting.fontSizeEditor).thenReturn(fontEditor)
        Mockito.`when`(fontEditor.text).thenReturn(fontText)
        Mockito.`when`(fontText.toString()).thenReturn(testFontSize.toString())

        Mockito.`when`(setting.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE)).
                thenReturn(sharePreference)
        Mockito.`when`(sharePreference.edit()).thenReturn(editor)
    }

    @After
    fun after(){

    }

    @Test
    fun itemSelect(){
        onOptionsItemSelected(finishItem, setting)
        verify(finishItem).itemId
        verify(setting).finish()
    }

    @Test
    fun updateConfigNormal(){
        updateConfig(setting)
        verify(setting).frequencyEditor
        verify(setting).fontSizeEditor

        verify(frequencyEditor).text
        verify(fontEditor).text

        verify(setting).getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE)
        verify(editor).apply()
    }

    @Test
    fun updateRemoteViewNormal(){
        updateRemoteView(wigdetManager, setting)
        verify(setting).fontSizeEditor
        verify(setting).getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE)
    }
}
