package com.rousci.androidapp.widgetnote.viewPresent.setting

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.EditText
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresent.defaultFrequency
import com.rousci.androidapp.widgetnote.viewPresent.frequency
import com.rousci.androidapp.widgetnote.viewPresent.singleDataPreference
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class Setting : AppCompatActivity() {
    var frequencyEditor:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        val toolbar = find<Toolbar>(R.id.toolbar1)
        setSupportActionBar(toolbar)

        frequencyEditor = findViewById(R.id.editText1)

        val frequency = getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(frequency, defaultFrequency)
        frequencyEditor!!.setText(frequency.toString())
    }

    override fun finish() {
        try {
            val frequencyEdited = frequencyEditor!!.text.toString().toInt()
        }
        catch (e:NumberFormatException){
            toast(R.string.invalidFormat)
            return
        }
        val frequencyEdited = frequencyEditor!!.text.toString().toInt()
        val editor = getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
        editor.putInt(frequency, frequencyEdited)
        editor.apply()
        super.finish()
    }
}