package com.rousci.androidapp.widgetnote.view.addNote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.widget.EditText

import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.view.mainActicity.MainActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class addNote : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val editText1 = find<EditText>(R.id.editText1)

        val toolBar = find<Toolbar>(R.id.toolbar1)
        setSupportActionBar(toolBar)

        val floatingActionButton = find<FloatingActionButton>(R.id.floatingActionButton1)
        floatingActionButton.setOnClickListener {
            insert(editText1.text.toString())
            startActivity<MainActivity>()
            finish()
        }
    }
}
