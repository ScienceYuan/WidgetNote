package com.rousci.androidapp.widgetnote.presenter.main

import com.rousci.androidapp.widgetnote.view.addNote.addNote
import com.rousci.androidapp.widgetnote.view.mainActicity.MainActivity

/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 */
var activity:MainActivity? = null

fun setPresenter(mainActivity: MainActivity){
    activity = mainActivity
}

fun onActionBtnClick(){
    activity!!.start<addNote>()
    activity!!.finish()
}