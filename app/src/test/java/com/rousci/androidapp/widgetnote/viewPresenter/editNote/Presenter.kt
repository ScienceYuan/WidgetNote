package com.rousci.androidapp.widgetnote.viewPresenter.editNote

import android.content.Intent
import android.text.Editable
import android.view.MenuItem
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.*
import com.rousci.androidapp.widgetnote.viewPresenter.noteId
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals

/**
 * Created by rousci on 17-11-10.
 */
@RunWith(RobolectricTestRunner::class)
class PresenterTest {

    lateinit var editNote: EditNote
    val item = mock(MenuItem::class.java)
    val editText = mock(EditText::class.java)
    val text = mock(Editable::class.java)
    val note = Note(1, "just a test")

    @Before
    fun initFunc() {
        ApplicationProvider.getApplicationContext<EditNote>().database.use {
            insert(noteTableName, idName to note.id, contentName to note.content)
        }

        editNote = Robolectric.buildActivity(EditNote::class.java,
                Intent(ApplicationProvider.getApplicationContext<EditNote>(),
                        EditNote::class.java).apply { this.putExtra(noteId, 1) }).setup().get()

        `when`(editText.text).thenReturn(text)
    }

    @Test
    fun testFinish() {
        val changedText = "changed"
        finishPR(editNote, this.note.id, changedText)
        assertEquals(changedText, editNote.database.use {
            select(noteTableName, contentName)
                    .whereArgs("$idName = {id}", "id" to this@PresenterTest.note.id)
                    .parseSingle(classParser<String>())
        })
    }

    @Test
    fun onRecoverySelect() {
        `when`(item.itemId).thenReturn(R.id.recovery)
        onItemSelectPR(item, editNote)
    }

    @Test
    fun onDelSelect() {
        `when`(item.itemId).thenReturn(R.id.del)
        onItemSelectPR(item, editNote)
        assertEquals(0, editNote.database.use {
            select(noteTableName, idName, contentName).parseList(classParser<Note>()).size
        })
    }

    @Test
    fun onHomeSelect() {
        `when`(item.itemId).thenReturn(android.R.id.home)
        onItemSelectPR(item, editNote)
    }
}