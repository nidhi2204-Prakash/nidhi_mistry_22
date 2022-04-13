package com.prakash.prakashjobapp

import android.app.Application

class JobApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    init {
        instance = this
    }

    companion object {
        private var instance: JobApplication? = null

        fun applicationContext(): JobApplication {
            return instance as JobApplication
        }
    }
}

