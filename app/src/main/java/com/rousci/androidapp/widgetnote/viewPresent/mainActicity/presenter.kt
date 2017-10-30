package com.rousci.androidapp.widgetnote.viewPresent.mainActicity

import android.app.Activity
import android.content.Intent
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.viewPresent.addNote.AddNote
import com.rousci.androidapp.widgetnote.viewPresent.passString
import com.rousci.androidapp.widgetnote.viewPresent.stringRequest
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast


/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 */

var getContext: () -> MainActivity = {null!!}

fun setPresenter(mainActivity: MainActivity){
    getContext = {mainActivity}
}

fun onActionBtnClick(){
    getContext().startActivityForResult<AddNote>(stringRequest)
}

fun onActivityResultPR(requestCode: Int, resultCode: Int, data: Intent?){
    if ((requestCode == stringRequest) and (resultCode == Activity.RESULT_OK)){
        val note = data!!.getStringExtra(passString)
        insert(note)
    }
}

fun onStartPR(){
    getContext().updateRecycleView()
}

fun onNavigationItemSelectedPR(item: MenuItem): Boolean {
    val drawer = getContext().find<DrawerLayout>(R.id.drawer)

    val callBacks = mapOf(

            R.id.setting to {
                getContext().toast("setting")
                drawer.closeDrawer(GravityCompat.START)
            },

            R.id.about to {
                val alertDialog = AlertDialog.Builder(getContext())
                alertDialog.setTitle(R.string.about)
                alertDialog.setMessage(R.string.about_content)
                alertDialog.setPositiveButton(R.string.sure, null)
                alertDialog.show()
            })
    callBacks[item.itemId]!!()
    return true
}