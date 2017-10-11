package com.rousci.androidapp.widgetnote.view.mainActicity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.insert
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.model.setdatabse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setdatabse(applicationContext)
        val b = findViewById(R.id.button)
        b.setOnClickListener {
            insert("test")
            Log.i("test", queryAll().toString())}
    }
}
