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

fun queryAll(){
    database!!.readableDatabase.select("notes_table", "content").parseList(classParser<String>())
}

fun insert(content: String){
    database!!.writableDatabase.insert("notes_table", "content" to content)
}