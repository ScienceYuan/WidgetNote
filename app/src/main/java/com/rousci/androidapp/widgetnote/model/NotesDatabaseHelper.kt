package com.rousci.androidapp.widgetnote.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by rousci on 17-10-11.
 * a standard anko databasePointer
 * anko is simple to use
 * I do not want to dear with complex api of tools
 * so I choose it
 */

class NotesDatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, databaseName, null, 1) {

 /* 
  * A static synchronized method for thread safe access
  * which returns a single instance of NotesDatabaseHelper
  */
    companion object {
        private var instance: NotesDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): NotesDatabaseHelper {
            if (instance == null) {
                instance = NotesDatabaseHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(dataBase: SQLiteDatabase) {
        dataBase.createTable(
                noteTableName,
                true,
                idName to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
                contentName to TEXT + NOT_NULL
        )
    }

    override fun onUpgrade(dataBase: SQLiteDatabase, p1: Int, p2: Int) {
        dataBase.dropTable(noteTableName, true)
        onCreate(dataBase)
    }

}
