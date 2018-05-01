package com.rousci.androidapp.widgetnote.viewPresenter.mainActivity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.viewPresenter.addNote.AddNote
import com.rousci.androidapp.widgetnote.viewPresenter.passString
import com.rousci.androidapp.widgetnote.viewPresenter.setting.Setting
import com.rousci.androidapp.widgetnote.viewPresenter.stringRequest
import com.rousci.androidapp.widgetnote.viewPresenter.writeFilePermission
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult


/**
 * Created by rousci on 17-10-23.
 *
 * trying to make a different MVP pattern
 */

fun onActionBtnClick(mainActivity: MainActivity){
    mainActivity.startActivityForResult<AddNote>(stringRequest)
}

fun onActivityResultPR(requestCode: Int, resultCode: Int, data: Intent?){
    if ((requestCode == stringRequest) and (resultCode == Activity.RESULT_OK)){
        val note = data!!.getStringExtra(passString)
        insert(note)
    }
}

fun onStartPR(mainActivity: MainActivity){
    mainActivity.updateRecycleView()
}

fun onNavigationItemSelectedPR(item: MenuItem, mainActivity: MainActivity): Boolean {
    when(item.itemId){
        R.id.setting -> {
            mainActivity.startActivity<Setting>()
        }

        R.id.about -> {
            val alertDialog = AlertDialog.Builder(mainActivity)
            alertDialog.setTitle(R.string.about)
            alertDialog.setMessage(R.string.about_content)
            alertDialog.setPositiveButton(R.string.sure, null)
            alertDialog.show()
        }
    }
    return true
}


/**
 * @param context the context of Setting activity
 *ask permission to read and write file to external storage
 */
fun getPermission(context: MainActivity){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if((context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) ||
                (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED)
        ) {
            context.requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE),
                    writeFilePermission)
        }
    }
}