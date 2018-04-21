package com.rousci.androidapp.widgetnote.viewPresenter.addNote

import android.app.Activity
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresenter.passString

/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 *
 * interface: assume that activity has a property named editText
 * and it is editable
 */
fun onOptionsItemSelectedPR(item: MenuItem, addNote: AddNote){
    when(item.itemId){
        R.id.delete -> {
            addNote.editText!!.setText("")
            addNote.finish()
        }
        R.id.home -> {
            addNote.finish()
        }
    }
}

fun finishPR(addNote: AddNote){
    val data = addNote.editText!!.text.toString()
    if(data != ""){
        val intent = addNote.intent
        intent.putExtra(passString, data)
        addNote.setResult(Activity.RESULT_OK,intent)
    }
}