package com.example.gymsaround

import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsDetailsViewModel : ViewModel() {
    private val gymsApiService: GymsApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gyms-295f1-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gymsApiService = retrofit.create(GymsApiService::class.java)
    }
}