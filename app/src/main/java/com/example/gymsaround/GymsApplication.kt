package com.example.gymsaround

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

// in order to make ViewModel get a reference to the context ,
// we performed this class
@HiltAndroidApp
class GymsApplication : Application()