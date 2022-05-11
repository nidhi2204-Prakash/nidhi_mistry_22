package com.example.prakashjobapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.prakashjobapp.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler(Looper.getMainLooper()).postDelayed({
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}