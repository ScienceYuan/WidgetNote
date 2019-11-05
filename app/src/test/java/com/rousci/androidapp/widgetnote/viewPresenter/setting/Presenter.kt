package com.rousci.androidapp.widgetnote.viewPresenter.setting

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.view.MenuItem
import android.widget.EditText
import com.rousci.androidapp.widgetnote.viewPresenter.singleDataPreference
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

/**
 * Created by rousci on 17-11-7.
 */

@RunWith(RobolectricTestRunner::class)
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
        `when`(finishItem.itemId).thenReturn(android.R.id.home)

        `when`(setting.frequencyEditor).thenReturn(frequencyEditor)
        `when`(frequencyEditor.text).thenReturn(frequencyText)
        `when`(frequencyText.toString()).thenReturn(testFrequence.toString())

        `when`(setting.fontSizeEditor).thenReturn(fontEditor)
        `when`(fontEditor.text).thenReturn(fontText)
        `when`(fontText.toString()).thenReturn(testFontSize.toString())

        `when`(setting.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE)).
                thenReturn(sharePreference)
        `when`(sharePreference.edit()).thenReturn(editor)
    }

    @After
    fun after(){

    }

    @Test
    fun itemSelect(){
        onOptionsItemSelectedPR(finishItem, setting)
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
