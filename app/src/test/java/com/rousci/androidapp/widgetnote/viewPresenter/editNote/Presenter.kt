package com.rousci.androidapp.widgetnote.viewPresenter.editNote

import android.text.Editable
import android.view.MenuItem
import android.widget.EditText
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.Note
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.mockito.Mockito.*
import org.robolectric.annotation.Config

/**
 * Created by rousci on 17-11-10.
 */
@RunWith(RobolectricTestRunner::class)
class PresenterTest{
    val editNote = mock(EditNote::class.java)
    val item = mock(MenuItem::class.java)
    val editText = mock(EditText::class.java)
    val text = mock(Editable::class.java)
    val note = Note(1, "just a test")
    @Before
    fun initFunc(){
        `when`(editNote.editText).thenReturn(editText)
        `when`(editText.text).thenReturn(text)
        `when`(editNote.id).thenReturn(this.note.id)
    }

    @Test(expected = NullPointerException::class)
    fun testFinish(){
        finishPR(editNote, this.note.id, this.note.content)
        verify(editNote).id
        verify(editNote).editText
        verify(editText).text
    }

    @Test
    fun onRecoverySelect(){
        `when`(item.itemId).thenReturn(R.id.recovery)
        onItemSelectPR(item, editNote)
        verify(item).itemId
        verify(editNote).editText
        verify(editText).setText(this.note.content)
    }

    @Test(expected = NullPointerException::class)
    fun onDelSelect(){
        `when`(item.itemId).thenReturn(R.id.del)
        onItemSelectPR(item, editNote)
        verify(editNote).finish()
    }

    @Test
    fun onHomeSelect(){
        `when`(item.itemId).thenReturn(android.R.id.home)
        onItemSelectPR(item, editNote)
        verify(editNote).finish()
    }


}