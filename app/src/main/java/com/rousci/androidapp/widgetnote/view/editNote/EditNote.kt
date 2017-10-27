package com.rousci.androidapp.widgetnote.view.editNote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu

import com.rousci.androidapp.widgetnote.R
import org.jetbrains.anko.find

class EditNote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val toolBar = find<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_note, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
