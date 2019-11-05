package com.rousci.androidapp.widgetnote.viewPresenter.editNote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.contentName
import com.rousci.androidapp.widgetnote.model.database
import com.rousci.androidapp.widgetnote.model.idName
import com.rousci.androidapp.widgetnote.model.noteTableName
import com.rousci.androidapp.widgetnote.viewPresenter.noteId
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find

class EditNote : AppCompatActivity() {

    var id = 0
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note)

        val toolBar = find<Toolbar>(R.id.toolbar1)
        toolBar.setTitle(R.string.editNote)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        editText = find(R.id.frequencyEditor)

        this.id = intent.getIntExtra(noteId, -1)

        editText.setText(database.use {
            select(noteTableName, contentName)
                    .whereArgs("$idName = {id}", "id" to this@EditNote.id)
                    .parseSingle(classParser<String>())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        onItemSelectPR(item!!, this)

        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        finishPR(this, this.id, this.editText.text.toString())
        super.finish()
    }
}