package com.rousci.androidapp.widgetnote.viewPresent.editNote

import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.del

import com.rousci.androidapp.widgetnote.model.update

/**
 * Created by rousci on 17-10-27.
 *
 */
var getContext:() -> EditNote = {null!!}

fun setPresenter(editNote: EditNote){
    getContext = {editNote}
}

fun onItemSelectPR(item: MenuItem){
    val text = getContext().editText!!.text.toString()

    val callBacks = mapOf(
            R.id.recovery to {
                getContext().editText!!.setText(getContext().dataSelect)
            },

            R.id.del to {
                del(getContext().dataSelect)
                getContext().finish()
            }
    )
    callBacks[item.itemId]!!()
}

fun finishPR(){
    update(getContext().dataSelect, getContext().editText!!.text.toString())
}