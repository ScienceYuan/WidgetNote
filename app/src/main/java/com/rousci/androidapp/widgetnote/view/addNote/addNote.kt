package com.rousci.androidapp.widgetnote.view.addNote

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.presenter.addNote.onOptionsItemSelectedPR
import com.rousci.androidapp.widgetnote.presenter.addNote.setPresenter
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class addNote : AppCompatActivity() {

    var editText1:EditText? = null

    /**
    * I do not know why it looks like this
    * */
    inline fun <reified T : Activity> start() = startActivity<T>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(this)

        setContentView(R.layout.activity_add_note)
        editText1 = find<EditText>(R.id.editText1)

        val toolbar = find<Toolbar>(R.id.toolbar1)
        toolbar.setTitle(R.string.addNote)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onOptionsItemSelectedPR(item)
        return super.onOptionsItemSelected(item)
    }
}
