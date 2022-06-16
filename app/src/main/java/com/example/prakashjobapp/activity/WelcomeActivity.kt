package com.example.prakashjobapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager

class WelcomeActivity : AppCompatActivity() {

    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        button = findViewById(R.id.button)
        button .setOnClickListener {

            val sessionManager = SessionManager(this)
            if(sessionManager.isLoggedIn())
            {
                startActivity(Intent(this,DashboardActivity::class.java))
            }else
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }
}