package com.rousci.androidapp.widgetnote.presenter.addNote

import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.view.addNote.addNote
import com.rousci.androidapp.widgetnote.view.mainActicity.MainActivity


/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 */
var activity:addNote? = null

fun setPresenter(addNote: addNote){
    activity = addNote
}

fun onOptionsItemSelectedPR(item: MenuItem): Boolean{
    val callBacks = hashMapOf(R.id.correct
            to
            {insert(activity!!.editText1!!.text.toString())
                activity!!.start<MainActivity>()
                activity!!.finish()})
    callBacks[item.itemId]!!()
    return activity!!.onOptionsItemSelected(item)
}

