package com.example.gymsaround.Gyms.data

import com.example.gymsaround.Gyms.domain.Gym
import com.example.gymsaround.Gyms.data.remote.GymsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsDetailsRepository {

    private val gymsApiService: GymsApiService = Retrofit.Builder()
        .baseUrl("https://gyms-295f1-default-rtdb.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GymsApiService::class.java)


    suspend fun getGymFromRemoteDB(id: Int) = withContext(Dispatchers.IO) {
        gymsApiService.getGym(id).values.first().let {
            Gym(
                id = it.id,
                name = it.name,
                address = it.address,
                isOpen = it.isOpen,
            )
        }
    }

}