package com.rousci.androidapp.widgetnote.viewPresenter.editNote

import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.del

import com.rousci.androidapp.widgetnote.model.update

/**
 * Created by rousci on 17-10-27.
 */
fun onItemSelectPR(item: MenuItem, editNote: EditNote){
    when(item.itemId){
        R.id.recovery -> {
            editNote.editText.setText(editNote.dataSelect)
        }
        R.id.del -> {
            del(editNote.dataSelect)
            editNote.finish()
        }
        android.R.id.home -> {
            editNote.finish()
        }
    }
}

fun finishPR(editNote: EditNote){
    update(editNote.dataSelect, editNote.editText.text.toString())
}