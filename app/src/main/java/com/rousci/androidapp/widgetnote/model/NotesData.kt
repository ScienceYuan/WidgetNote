package com.rousci.androidapp.widgetnote.model

import android.content.Context
import org.jetbrains.anko.db.*

/**
 * Created by rousci on 17-10-11.
 * this mean to make a simple interface to keep notes
 *
 * database must init
 * but the expect of null database is still not deal with
 *
 * use a tick of closure
 * let the pointer of database can not be change
 */

var getDatabase: () -> NotesDatabaseHelper? = {null}

fun setDatabase(context: Context): Boolean {
    val database: NotesDatabaseHelper? = NotesDatabaseHelper(context)
    getDatabase = {database}
    return true
}

fun queryAll(): List<String> {
    val data =  getDatabase()!!.readableDatabase.select(noteTableName, contentName).parseList(classParser<String>())
    return data
}

fun insert(content: String){
    getDatabase()!!.writableDatabase.insert(noteTableName, contentName to content)
}