package com.rousci.androidapp.widgetnote.viewPresenter.addNote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.rousci.androidapp.widgetnote.R
import org.jetbrains.anko.find

class AddNote : AppCompatActivity() {

    lateinit var editText:EditText

    /**
    * I do not know why it looks like this
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_note)
        editText = find<EditText>(R.id.frequencyEditor)

        val toolbar = find<Toolbar>(R.id.toolbar1)
        toolbar.setTitle(R.string.addNote)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onOptionsItemSelectedPR(item, this)
        return super.onOptionsItemSelected(item)
    }

    override fun finish(){
        finishPR(this)
        return super.finish()
    }

    fun giveUpAndFinish(){
        return super.finish()
    }
}
