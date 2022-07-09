package com.example.myboxoffice.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myboxoffice.R
import com.example.myboxoffice.base.activity.BaseActivity
import com.example.myboxoffice.ui.home.HomeActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        putDelay()
    }
    private fun putDelay() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 2000)
    }
}