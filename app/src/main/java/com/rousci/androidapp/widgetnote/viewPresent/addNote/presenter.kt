package com.rousci.androidapp.widgetnote.viewPresent.addNote

import android.app.Activity
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresent.passString

/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 *
 * interface: assume that activity has a property named editText1
 * and it is editable
 */
var getContext: () -> AddNote = {null!!}

fun setPresenter(addNote: AddNote){
    getContext = {addNote}
}

fun onOptionsItemSelectedPR(item: MenuItem){
    when(item.itemId){
        R.id.del -> {
            getContext().giveUpAndFinish()
        }
        android.R.id.home -> {
            getContext().finish()
        }
    }
}

fun finishPR(){
    val data = getContext().editText1!!.text.toString()
    if(data != ""){
        val intent = getContext().intent
        intent.putExtra(passString, data)
        getContext().setResult(Activity.RESULT_OK,intent)
    }
}