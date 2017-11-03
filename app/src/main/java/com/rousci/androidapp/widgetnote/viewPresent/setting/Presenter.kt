package com.rousci.androidapp.widgetnote.viewPresent.setting

import android.content.Context
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresent.*
import org.jetbrains.anko.toast

/**
 * Created by rousci on 17-11-2.
 */
var getContext: () -> Setting = {null!!}

fun setPresenter(setting: Setting){
    getContext = {setting}
}

fun finishPR(){
    val frequencyEdited = getContext().frequencyEditor!!.text.toString().toInt()
    val fontEdited = getContext().fontSizeEditor!!.text.toString().toFloat()

    val editor = getContext().getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
    editor.putInt(frequency, frequencyEdited)
    editor.putFloat(fontSP, fontEdited)
    val time = getContext().getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(timeCounter, defaultFrequency)
    if (time >= frequencyEdited){
        editor.putInt(timeCounter, 0)
    }
    editor.apply()
}

fun onOptionsItemSelectedPR(item: MenuItem){
    when(item.itemId){
        android.R.id.home -> {
            getContext().finish()
        }
    }
}