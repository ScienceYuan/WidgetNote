package com.rousci.androidapp.widgetnote.viewPresent.editNote

import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.del

import com.rousci.androidapp.widgetnote.model.update

/**
 * Created by rousci on 17-10-27.
 */
var getContext:() -> EditNote = {null!!}

fun setPresenter(editNote: EditNote){
    getContext = {editNote}
}

fun onItemSelectPR(item: MenuItem){
    when(item.itemId){
        R.id.recovery -> {
            getContext().editText!!.setText(getContext().dataSelect)
        }
        R.id.del -> {
            del(getContext().dataSelect)
            getContext().finish()
        }
        android.R.id.home -> {
            getContext().finish()
        }
    }
}

fun finishPR(){
    update(getContext().dataSelect, getContext().editText!!.text.toString())
}