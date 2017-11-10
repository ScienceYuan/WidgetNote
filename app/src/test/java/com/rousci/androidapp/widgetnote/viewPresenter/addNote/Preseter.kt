package com.rousci.androidapp.widgetnote.viewPresenter.addNote

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.view.MenuItem
import android.widget.EditText
import com.rousci.androidapp.widgetnote.BuildConfig
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresenter.passString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.mockito.Mockito
import org.mockito.Mockito.*

/**
 * Created by rousci on 17-11-10.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, buildDir = "app/build")
class Presenter {
    val addNote = mock(AddNote::class.java)
    val text = mock(Editable::class.java)
    val editText = mock(EditText::class.java)
    val intent = mock(Intent::class.java)
    val item = mock(MenuItem::class.java)

    @Before
    fun initFunc(){
        Mockito.`when`(addNote.editText).thenReturn(editText)
        Mockito.`when`(editText.text).thenReturn(text)
        Mockito.`when`(addNote.intent).thenReturn(intent)
    }

    @Test
    fun finish(){
        finishPR(addNote)
        verify(addNote).editText
        verify(editText).text
        verify(addNote).intent
        verify(intent).putExtra(eq(passString), ArgumentMatchers.anyString())
        verify(addNote).setResult(Activity.RESULT_OK, intent)
    }

    @Test
    fun onHomeSelect(){
        Mockito.`when`(item.itemId).thenReturn(android.R.id.home)
        onOptionsItemSelectedPR(item, addNote)
        verify(item).itemId
        verify(addNote).finish()
    }

    @Test
    fun onDeleteSelect(){
        Mockito.`when`(item.itemId).thenReturn(R.id.delete)
        onOptionsItemSelectedPR(item, addNote)
        verify(addNote).editText
        verify(editText).setText(eq(""))
        verify(addNote).finish()
    }
}