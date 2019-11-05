package com.rousci.androidapp.widgetnote.viewPresenter.editNote

import android.content.Context
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

/**
 * Created by rousci on 17-10-27.
 */
fun onItemSelectPR(item: MenuItem, editNote: EditNote){
    when(item.itemId){
        R.id.recovery -> {
            editNote.editText.setText(
                    editNote.database.use {
                        select(noteTableName, contentName)
                                .whereArgs("$idName = {id}", "id" to editNote.id)
                                .parseSingle(classParser<String>())
                    })
        }
        R.id.del -> {
            editNote.database.use {
                delete(noteTableName, "$idName = {id}", "id" to editNote.id)
            }
            editNote.finish()
        }
        android.R.id.home -> {
            editNote.finish()
        }
    }
}

fun finishPR(context: Context, id: Int, content: String){
    context.database.use {
        update(noteTableName, contentName to content)
                .whereArgs("$idName = {id}", "id" to id)
                .exec()
    }
}
