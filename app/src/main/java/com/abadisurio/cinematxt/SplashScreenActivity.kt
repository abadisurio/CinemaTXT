package com.abadisurio.cinematxt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.abadisurio.cinematxt.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut:Long = 1000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(mainLooper).postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, HomeActivity::class.java))

            // close this activity
            finish()
        }, splashTimeOut)
    }
}