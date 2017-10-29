package com.rousci.androidapp.widgetnote.viewPresent.editNote

import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.del
import com.rousci.androidapp.widgetnote.model.setDatabase
import com.rousci.androidapp.widgetnote.model.update

/**
 * Created by rousci on 17-10-27.
 */
var activity:EditNote? = null

fun setPresenter(editNote: EditNote){
    setDatabase(editNote.applicationContext)
    activity = editNote
}

fun onItemSelectPR(item: MenuItem){
    val text = activity!!.editText!!.text.toString()

    val callBacks = mapOf(
            R.id.save to {
                update(activity!!.dataSelect, text)
                activity!!.finish()
            },

            R.id.del to {
                del(activity!!.dataSelect)
                activity!!.finish()
            }
    )

    callBacks[item!!.itemId]!!()
}