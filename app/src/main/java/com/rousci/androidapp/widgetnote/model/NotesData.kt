package com.rousci.androidapp.widgetnote.model

import android.content.Context
import android.util.Log
import org.jetbrains.anko.db.*

/**
 * Created by rousci on 17-10-11.
 * this mean to make a simple interface to keep notes
 *
 * databasePointer must init
 * but the expect of null databasePointer is still not deal with
 *
 * use a tick of closure
 * let the appContext of databasePointer can not be change
 */

var databasePointer:NotesDatabaseHelper? = null

fun setDatabase(context: Context) {
    val database: NotesDatabaseHelper? = NotesDatabaseHelper(context)
    databasePointer = database
}

fun queryAll(): List<String> {
    val data =  databasePointer!!.readableDatabase.select(noteTableName, contentName)
            .parseList(classParser<String>())
    databasePointer!!.close()
    return data
}

fun query(id: Int): String{
    val data = databasePointer!!.readableDatabase.select(noteTableName, contentName)
            .whereArgs("$idName = $id")
            .parseSingle(classParser<String>())
    databasePointer!!.close()
    return data
}

fun insert(content: String){
    databasePointer!!.writableDatabase.insert(noteTableName, contentName to content)
    databasePointer!!.close()
}

fun update(before: String, after: String){
    databasePointer!!.writableDatabase.update(noteTableName, contentName to after).where("$contentName = '$before'").exec()
    databasePointer!!.close()
}

fun del(content: String){
    databasePointer!!.writableDatabase.delete(noteTableName, "$contentName = '$content'")
    databasePointer!!.close()
}
