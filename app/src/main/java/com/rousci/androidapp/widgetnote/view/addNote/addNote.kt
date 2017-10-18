package com.rousci.androidapp.widgetnote.view.addNote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
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

        val toolBar = find<Toolbar>(R.id.toolbar1)
        setSupportActionBar(toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.correct, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val editText1 = find<EditText>(R.id.editText1)
        val callBacks = hashMapOf(R.id.correct to {insert(editText1.text.toString());startActivity<MainActivity>();finish()})
        callBacks[item.itemId]!!()
        return super.onOptionsItemSelected(item)
    }
}
