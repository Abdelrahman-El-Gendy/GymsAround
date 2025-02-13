package com.example.gymsaround

import android.app.Application
import android.content.Context

// in order to make ViewModel get a reference to the context ,
// we performed this class
class GymsApplication : Application() {

    init {
        application = this
    }

    companion object {
        private lateinit var application: GymsApplication
        fun getApplicationContext(): Context = application.applicationContext
    }
}