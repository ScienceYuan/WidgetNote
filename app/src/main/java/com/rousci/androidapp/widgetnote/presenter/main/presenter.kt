package com.rousci.androidapp.widgetnote.presenter.main

import android.app.Activity
import android.content.Intent
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.presenter.requestName
import com.rousci.androidapp.widgetnote.presenter.requestString
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
    val intent:Intent = Intent(activity!!.applicationContext, addNote::class.java)
    activity!!.start(intent, requestString)
}

fun onActivityResultPR(requestCode: Int, resultCode: Int, data: Intent?){
    if ((requestCode == requestString) and (resultCode == Activity.RESULT_OK)){
        val note = data!!.getStringExtra(requestName)
        insert(note)
        activity!!.dataSet.add(note)
        activity!!.recycleView!!.adapter.notifyDataSetChanged()
    }
}