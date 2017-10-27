package com.rousci.androidapp.widgetnote.presenter.main

import android.app.Activity
import android.content.Intent
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.presenter.passString
import com.rousci.androidapp.widgetnote.presenter.stringRequest
import com.rousci.androidapp.widgetnote.view.addNote.addNote
import com.rousci.androidapp.widgetnote.view.mainActicity.MainActivity
import org.jetbrains.anko.startActivityForResult


/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 */
var activity: MainActivity? = null

fun setPresenter(mainActivity: MainActivity){
    activity = mainActivity
}

fun onActionBtnClick(){
    activity!!.startActivityForResult<addNote>(stringRequest)
}

fun onActivityResultPR(requestCode: Int, resultCode: Int, data: Intent?){
    if ((requestCode == stringRequest) and (resultCode == Activity.RESULT_OK)){
        val note = data!!.getStringExtra(passString)
        insert(note)
        activity!!.updateRecycleView()
    }
}