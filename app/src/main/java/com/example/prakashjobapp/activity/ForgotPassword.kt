package com.example.prakashjobapp.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.prakashjobapp.R

class ForgotPassword : AppCompatActivity() {
    lateinit var backarrow_password :ImageView
    lateinit var send_button :Button
    lateinit var Login_text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        backarrow_password = findViewById(R.id.backarrow_password)
        send_button = findViewById(R.id.send_button)
        Login_text = findViewById(R.id.Login_text)
        backarrow_password.setOnClickListener {
            onBackPressed()
        }
        send_button.setOnClickListener {

        }
        Login_text.setOnClickListener {

        }
    }

}