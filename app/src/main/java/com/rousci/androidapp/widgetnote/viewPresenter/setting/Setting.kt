package com.rousci.androidapp.widgetnote.viewPresenter.setting

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.setDatabase
import com.rousci.androidapp.widgetnote.viewPresenter.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

/**
*this activity initialize its content pointer
* when it is created,
* update all widgets when it is finished
* */
class Setting : AppCompatActivity() {
    var frequencyEditor:EditText? = null
    var fontSizeEditor:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        setDatabase(this)
        val toolbar = find<Toolbar>(R.id.toolbar1)
        toolbar.setTitle(R.string.setting)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        frequencyEditor = find(R.id.frequencyEditor)
        fontSizeEditor = find(R.id.fontSize)

        val frequencyStored = getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(frequency, defaultFrequency)
        val fontSize = getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getFloat(fontSP, fontSPDefault)

        fontSizeEditor!!.setText(fontSize.toString())
        frequencyEditor!!.setText(frequencyStored.toString())

        val importBtn = find<Button>(R.id.button_import)
        val outputBtn = find<Button>(R.id.button_output)
        importBtn.onClick {
            importData(this@Setting)
        }
        outputBtn.onClick {
            outputData(this@Setting)
        }
    }

    override fun finish() {
        try {
            updateConfig(this)
            updateRemoteView(AppWidgetManager.getInstance(this), this)
            super.finish()
        }
        catch (e:NumberFormatException){
            toast(R.string.invalidFormat)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onOptionsItemSelected(item, this)
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        onActivityResultPR(requestCode, resultCode, data, this)
        super.onActivityResult(requestCode, resultCode, data)
    }
}