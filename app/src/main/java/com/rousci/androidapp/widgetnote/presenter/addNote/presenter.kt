package com.rousci.androidapp.widgetnote.presenter.addNote

import android.app.Activity
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.presenter.passString
import com.rousci.androidapp.widgetnote.view.addNote.addNote


/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 *
 * interface: assume that activity has a property named editText1
 * and it is editable
 */
var activity:addNote? = null

fun setPresenter(addNote: addNote){
    activity = addNote
}

fun onOptionsItemSelectedPR(item: MenuItem?){
    val callBacks = hashMapOf(R.id.correct
            to
            {   val data = activity!!.editText1!!.text.toString()
                val intent = activity!!.intent
                intent.putExtra(passString, data)
                activity!!.setResult(Activity.RESULT_OK,intent)
                activity!!.finish()})
    callBacks[item!!.itemId]!!()
}

