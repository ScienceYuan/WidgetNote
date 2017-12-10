package com.rousci.androidapp.widgetnote.viewPresenter.setting

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.viewPresenter.*
import com.rousci.androidapp.widgetnote.viewPresenter.widget.NoteWidget
import org.jetbrains.anko.toast
import java.io.File
import java.io.FileInputStream

/**
 * Created by rousci on 17-11-2.
 * last modify on 17-14-4
 */


/**
 * @param context the context to do next things
 * this function read configs from the activity of setting,
 * and configs will upload to sharedPreferences */
fun updateConfig(context: Setting){
    val frequencyEdited = context.frequencyEditor!!.text.toString().toInt()
    val fontEdited = context.fontSizeEditor!!.text.toString().toFloat()

    val editor = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
    editor.putInt(frequency, frequencyEdited)
    editor.putFloat(fontSP, fontEdited)

    editor.apply()
}

/**
 * @param appWidgetManager appWidgetManager to update widget directly
 * @param context a context that read configs from
 * this function will change all the widget that the widgetManager controls
 * by the config read from  context's sharedPreference*/
fun updateRemoteView(appWidgetManager: AppWidgetManager, context: Setting){
    val fontEdited = context.fontSizeEditor!!.text.toString().toFloat()
    val lastNote = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).
            getString(lastChoicedNote, "没有添加数据")

    val views = RemoteViews(context.packageName, R.layout.note_widget)
    val componentName = ComponentName(context, NoteWidget::class.java)
    views.setTextViewText(R.id.appwidget_text, lastNote)
    views.setTextViewTextSize(R.id.appwidget_text, TypedValue.COMPLEX_UNIT_SP ,fontEdited)
    appWidgetManager.updateAppWidget(componentName, views)
}

/**
 * @param item the item be selected
 * @param context the activity be presented
 * do some things that depend on the different items*/
fun onOptionsItemSelected(item: MenuItem, context: Activity){
    when(item.itemId){
        android.R.id.home -> {
            context.finish()
        }
    }
}

/**
 * @param context the context to make intent
 * the intent should start other activities to choice a file
 * and then the context get back a intent,
 * so it can get the path of the file passed by the intent it gets back*/
fun importData(context: Setting){
    val localData = Intent(Intent.ACTION_GET_CONTENT)
    localData.putExtra(Intent.EXTRA_LOCAL_ONLY, Intent.EXTRA_LOCAL_ONLY)
    localData.type = "text/*"
    context.startActivityForResult(localData, getLocal)
}

/**
 * @param context the context to make intent
 * the intent should start other activities to choice a folder
 * then the content get back an intent to read the path
 * so the context can make a file that stores data of user*/
fun outputData(context: Setting){
    val folderPath = Environment.getExternalStorageDirectory().path + backupFolderPath
    val filePath = Environment.getExternalStorageDirectory().path + backupFolderPath + backupFileName
    val storageFolder = File(folderPath)
    val backupFile = File(filePath)
    if (!storageFolder.exists()){
        storageFolder.mkdirs()
    }
    if (!backupFile.exists()){
        backupFile.createNewFile()
    }

    val noteList = queryAll()
    val stringWriteOut = parseToString(noteList)

    backupFile.writeBytes(stringWriteOut.toByteArray())
    context.toast(R.string.backupSucceed)
}

/**
 * @param requestCode passing the param of the function which be presented
 * it should means the request type of the intent,it was set when the intent was built
 * @param resultCode means the status of the intent be started.It could be succeed or failed,
 * or something other.
 * @param data the intent passed between activities,it stores the data of message.
 * */
fun onActivityResultPR(requestCode: Int, resultCode: Int, data: Intent?, context: Setting) {

    /**
     * @param uri The sting value of uri that ready be parse to absolute path
     * it should be "file:// ...",and should not support other formats
     * @return the value the uri string parsed to
     * */
    fun getPath(uri: String):String{
        fun getStart(s: String, num:Int):String = s.filterIndexed{ index, _ -> index < num}
        fun getRest(s: String, num: Int):String = s.filterIndexed { index, _ -> index >= num}
        if (getStart(uri, 4) == "file"){
            return getRest(uri, 7)
        }
        else{
            return uri
        }
    }

    if (requestCode == getLocal && resultCode == Activity.RESULT_OK){
        val filePath =getPath(data!!.dataString)
        Log.i("path", filePath)

        val file = File(filePath)
        val fileIS = FileInputStream(file)
        val buffer = ByteArray(fileIS.available())
        fileIS.read(buffer)
        val content = String(buffer)
        val notes = parseFromString(content)
        notes.forEach { insert(it) }
    }
}