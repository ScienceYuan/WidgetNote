package com.rousci.androidapp.widgetnote.model

import android.content.Context
import org.jetbrains.anko.db.*

/**
 * Created by rousci on 17-10-11.
 * this mean to make a simple interface to keep notes
 *
 * database must init
 * but the expect of null database is still not deal with
 */


var database: NotesDatabaseHelper? = null

fun setdatabse(context: Context): Boolean {
    database = NotesDatabaseHelper(context)
    return true
}

fun queryAll(): List<String> {
    val data =  database!!.readableDatabase.select(noteTableName, contentName).parseList(classParser<String>())
    return data
}

fun insert(content: String){
    database!!.writableDatabase.insert(noteTableName, contentName to content)
}