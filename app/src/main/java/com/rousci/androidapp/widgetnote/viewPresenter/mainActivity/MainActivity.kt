package com.rousci.androidapp.widgetnote.viewPresenter.mainActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.model.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var recycleView: RecyclerView
    lateinit var toolbar:Toolbar
    lateinit var drawer:DrawerLayout
    lateinit var actionButton:FloatingActionButton
    val dataSet = mutableListOf<Note>()

    fun updateRecycleView(){
        dataSet.clear()
        dataSet.addAll(database.use {
            select(noteTableName, idName, contentName).parseList(classParser<Note>())
        })
        recycleView.adapter!!.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        getPermission(this)

        toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = find<DrawerLayout>(R.id.drawer)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navView = find<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        recycleView = find<RecyclerView>(R.id.recycleView)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleView.layoutManager = layoutManager
        recycleView.adapter = NoteRecycleAdapter(dataSet, this)
        updateRecycleView()

        actionButton = find<FloatingActionButton>(R.id.floatingActionButton1)
        actionButton.setOnClickListener {
            onActionBtnClick(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onActivityResultPR(this, requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        onStartPR(this)
        super.onStart()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return onNavigationItemSelectedPR(item, this)
    }
}