package com.andygeek.appbar_bottom

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.SearchEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private var mBottomAppBar : BottomAppBar? = null
    private var btn_activar : Button? = null
    private var btn_desactivar : Button? = null
    private var fab : FloatingActionButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomAppBar = findViewById(R.id.bottom_app_bar)
        setSupportActionBar(mBottomAppBar)

        fab = findViewById(R.id.btn_fab)

        btn_activar = findViewById(R.id.btn_activar)
        btn_desactivar = findViewById(R.id.btn_desactivar)

        btn_activar?.setOnClickListener {
            mBottomAppBar?.fabAlignmentMode=BottomAppBar.FAB_ALIGNMENT_MODE_END


            mBottomAppBar?.menu?.getItem(0)?.isVisible = false
            mBottomAppBar?.navigationIcon = null

            Handler().postDelayed(Runnable {
                mBottomAppBar?.menu?.getItem(1)?.isVisible = true
                mBottomAppBar?.menu?.getItem(2)?.isVisible = true
                mBottomAppBar?.menu?.getItem(3)?.isVisible = true
            },300)


        }
        btn_desactivar?.setOnClickListener {
            mBottomAppBar?.fabAlignmentMode=BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

            mBottomAppBar?.menu?.getItem(1)?.isVisible = false
            mBottomAppBar?.menu?.getItem(2)?.isVisible = false
            mBottomAppBar?.menu?.getItem(3)?.isVisible = false
            Handler().postDelayed(Runnable {
                mBottomAppBar?.menu?.getItem(0)?.isVisible = true
                mBottomAppBar?.setNavigationIcon(R.drawable.ic_baseline_menu_24)
            },300)

        }

        mBottomAppBar?.setOnMenuItemClickListener {menuItem ->
            when (menuItem.itemId) {
                R.id.app_bar_search -> {
                    println("dddddssdd")
                    true
                }
                else -> false
            }
        }


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.bottom_app_bar_menu, menu)
        menu?.getItem(0)?.isVisible = true
        menu?.getItem(1)?.isVisible = false
        menu?.getItem(2)?.isVisible = false
        menu?.getItem(3)?.isVisible = false

        var menuItem : MenuItem = menu!!.findItem(R.id.app_bar_search)
        var searchView : SearchView = menuItem.actionView as SearchView
        searchView.setOnSearchClickListener {
            println("Click")
            fab!!.hide()

        }
        searchView.setOnCloseListener {
            fab!!.show()
            false
        }


        return  super.onCreateOptionsMenu(menu)
    }




}