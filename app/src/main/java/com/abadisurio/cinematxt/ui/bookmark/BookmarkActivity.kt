package com.abadisurio.cinematxt.ui.bookmark

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.abadisurio.cinematxt.R
import com.abadisurio.cinematxt.databinding.ActivityBookmarkBinding

class BookmarkActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBookmarkBinding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(activityBookmarkBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityBookmarkBinding.viewPager.adapter = sectionsPagerAdapter
        activityBookmarkBinding.tabs.setupWithViewPager(activityBookmarkBinding.viewPager)
        supportActionBar?.hide()

        toolbar = findViewById(R.id.toolbar)
        toolbar?.inflateMenu(R.menu.menu_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        activityBookmarkBinding.toolbar.setTitleTextColor(Color.BLACK)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}