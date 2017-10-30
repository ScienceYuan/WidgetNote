package com.rousci.androidapp.widgetnote.viewPresent.mainActicity

import android.app.Activity
import android.content.Intent
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.viewPresent.passString
import com.rousci.androidapp.widgetnote.viewPresent.stringRequest
import com.rousci.androidapp.widgetnote.viewPresent.addNote.addNote
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast


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
    }
}

fun onStartPR(){
    activity!!.updateRecycleView()
}

fun onNavigationItemSelectedPR(item: MenuItem): Boolean {
    val drawer = activity!!.find<DrawerLayout>(R.id.drawer)

    val callBacks = mapOf(

            R.id.setting to {
                activity!!.toast("setting")
                drawer.closeDrawer(GravityCompat.START)
            },

            R.id.about to {
                val alertDialog = AlertDialog.Builder(activity!!)
                alertDialog.setTitle(R.string.about)
                alertDialog.setMessage(R.string.about_content)
                alertDialog.setPositiveButton(R.string.sure, null)
                alertDialog.show()
            })
    return true
}