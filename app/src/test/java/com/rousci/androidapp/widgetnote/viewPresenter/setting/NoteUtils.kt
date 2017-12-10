package com.rousci.androidapp.widgetnote.viewPresenter.setting

import com.rousci.androidapp.widgetnote.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by rousci on 17-12-9.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, buildDir = "app/build")
class utilTest(){
    val commonString = "${separateSymbol}test1${separateSymbol}test2${separateSymbol}"
    val singleString = "${separateSymbol}test${separateSymbol}"
    val emptyString = "${separateSymbol}${separateSymbol}"
    @Test
    fun commonParseToTest(){
        assert(parseFromString(commonString) == listOf("test1", "test2"))
        assert(parseFromString(singleString) == listOf("test"))
    }

    @Test
    fun commonParseFromTest(){
        assert(parseToString(listOf("test")) == separateSymbol+"test"+ separateSymbol)
    }

    @Test
    fun parseFromEmpty(){
        assert(parseFromString(emptyString) == listOf<String>())
    }
}