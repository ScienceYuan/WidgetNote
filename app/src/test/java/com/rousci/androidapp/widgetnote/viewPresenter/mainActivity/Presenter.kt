package com.rousci.androidapp.widgetnote.viewPresenter.mainActivity

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresenter.addNote.AddNote
import com.rousci.androidapp.widgetnote.viewPresenter.passString
import com.rousci.androidapp.widgetnote.viewPresenter.setting.Setting
import com.rousci.androidapp.widgetnote.viewPresenter.stringRequest
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner

/**
 * Created by rousci on 17-11-10.
 */

@RunWith(RobolectricTestRunner::class)
class Presenter{
    val mainActivity = mock(MainActivity::class.java)
    val intent = Intent()
    val requestCode = stringRequest
    val resultCode = Activity.RESULT_OK
    val item = mock(MenuItem::class.java)

    @Before
    fun initFunc(){
        Mockito.`when`(item.itemId).thenReturn(R.id.setting)
    }

    @Test
    fun actionBtnClk(){
        onActionBtnClick(mainActivity)
        verify(mainActivity).startActivityForResult<AddNote>(stringRequest)
    }

    @Test(expected = IllegalStateException::class)
    fun onResult(){
        onActivityResultPR(mainActivity, requestCode, resultCode, intent)
        verify(intent).getStringExtra(passString)
    }

    @Test
    fun onStart(){
        onStartPR(mainActivity)
        verify(mainActivity).updateRecycleView()
    }

    @Test
    fun navigationItemSelectSetting(){
        onNavigationItemSelectedPR(item, mainActivity)
        verify(mainActivity).startActivity<Setting>()
    }
}