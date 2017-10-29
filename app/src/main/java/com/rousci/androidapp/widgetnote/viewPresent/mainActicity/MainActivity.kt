package com.rousci.androidapp.widgetnote.viewPresent.mainActicity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.queryAll
import com.rousci.androidapp.widgetnote.model.setDatabase
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    var recycleView: RecyclerView? = null
    var toolbar:Toolbar? = null
    var drawer:DrawerLayout? = null
    var actionButton:FloatingActionButton? = null
    val dataSet = mutableListOf<String>()

    fun updateRecycleView(){
        dataSet.clear()
        dataSet.addAll(queryAll())
        recycleView!!.adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        setPresenter(this)
        setDatabase(applicationContext)

        toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = find<DrawerLayout>(R.id.drawer)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer!!.setDrawerListener(toggle)
        toggle.syncState()

        recycleView = find<RecyclerView>(R.id.recycleView)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleView!!.layoutManager = layoutManager
        recycleView!!.adapter = StringRecycleAdapter(dataSet, this)
        updateRecycleView()

        actionButton = find<FloatingActionButton>(R.id.floatingActionButton1)
        actionButton!!.setOnClickListener {
            onActionBtnClick()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onActivityResultPR(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        onStartPR()
        super.onStart()
    }

}