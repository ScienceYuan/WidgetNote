package com.rousci.androidapp.widgetnote.viewPresenter.setting

import android.view.MenuItem
import com.rousci.androidapp.widgetnote.BuildConfig
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by rousci on 17-11-7.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, buildDir = "app/build")
class PresenterTest{

    val contextController = Robolectric.buildActivity(Setting::class.java)
    val context = contextController.get()
    val finishItem = mock(MenuItem::class.java)

    @Before
    fun before(){
        contextController.setup()
        Mockito.`when`(finishItem.itemId).thenReturn(android.R.id.home)
    }

    @After
    fun after(){
        contextController.destroy()
    }

    @Test
    fun itemSelect(){
        onOptionsItemSelected(finishItem ,context)
        verify(finishItem).itemId
    }

    @Test
    fun finishTest(){
        finish(context)
    }
}
