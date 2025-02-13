package com.example.gymsaround

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsScreenRepository {
    private val gymsDao = GymsDatabase.getDaoInstance(GymsApplication.getApplicationContext())

    private var apiService: GymsApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://gyms-295f1-default-rtdb.firebaseio.com/")
            .build()
            .create(GymsApiService::class.java)

     suspend fun toggleFavouriteGym(gymId: Int, newFavouriteGym: Boolean) =
        withContext(Dispatchers.IO) {
            gymsDao.update(
                GymFavouriteState(
                    id = gymId, isFavourite = newFavouriteGym
                )
            )
            return@withContext gymsDao.getAllGyms()
        }

     suspend fun getAllGyms() = withContext(Dispatchers.IO) {
        try {
            updateLocalDatabase()
        } catch (e: Exception) {
            if (gymsDao.getAllGyms()
                    .isEmpty()
            ) throw Exception("Something went wrong. No data was found, Try connecting to Internet")
        }
        gymsDao.getAllGyms()
    }

     suspend fun updateLocalDatabase() {
        val gyms = apiService.getGyms()

        val favouriteGymsList = gymsDao.getFavouriteGyms()

        gymsDao.addAllGyms(gyms)

        gymsDao.updateAll(favouriteGymsList.map {
            GymFavouriteState(
                id = it.id, true
            )
        })
    }
}