package com.rousci.androidapp.widgetnote.viewPresenter.editNote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.viewPresenter.notePosition
import org.jetbrains.anko.find

class EditNote : AppCompatActivity() {

    val dataSet:MutableList<String> = mutableListOf()
    lateinit var dataSelect:String
    lateinit var editText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note)

        val toolBar = find<Toolbar>(R.id.toolbar1)
        toolBar.setTitle(R.string.editNote)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        editText = find(R.id.frequencyEditor)

        val position = intent.getIntExtra(notePosition, -1)


        dataSet.addAll(queryAll())
        dataSelect = dataSet[position]

        editText.setText(dataSelect)
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
        finishPR(this)
        super.finish()
    }
}