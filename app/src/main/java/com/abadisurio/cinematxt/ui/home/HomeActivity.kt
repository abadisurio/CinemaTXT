package com.abadisurio.cinematxt.ui.home

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.abadisurio.cinematxt.R
import com.abadisurio.cinematxt.databinding.ActivityHomeBinding
import com.abadisurio.cinematxt.ui.bookmark.BookmarkActivity

class HomeActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        setSupportActionBar(toolbar)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.inflateMenu(R.menu.menu_detail)
        toolbar?.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.action_bookmark -> {
                    startActivity(Intent(this, BookmarkActivity::class.java))
                }
            }
            true
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)
//        supportActionBar?.hide()
//        toolbar?.
        activityHomeBinding.toolbar.setTitleTextColor(Color.BLACK)
    }
}