package com.rousci.androidapp.widgetnote.viewPresent.editNote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.viewPresent.notePosition
import org.jetbrains.anko.find

class EditNote : AppCompatActivity() {

    val dataSet:MutableList<String> = mutableListOf()
    var dataSelect:String = ""
    var editText:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        setPresenter(this)

        val toolBar = find<Toolbar>(R.id.toolbar2)
        toolBar.setTitle(R.string.editNote)
        setSupportActionBar(toolBar)

        editText = find(R.id.editText2)

        val position = intent.getIntExtra(notePosition, -1)


        dataSet.addAll(queryAll())
        dataSelect = dataSet[position]

        editText!!.setText(dataSelect)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        onItemSelectPR(item!!)

        return super.onOptionsItemSelected(item)
    }
}