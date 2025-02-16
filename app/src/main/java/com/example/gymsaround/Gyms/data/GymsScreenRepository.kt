package com.example.gymsaround.Gyms.data

import com.example.gymsaround.Gyms.data.local.GymsDao
import com.example.gymsaround.Gyms.domain.Gym
import com.example.gymsaround.Gyms.data.remote.GymsApiService
import com.example.gymsaround.GymsApplication
import com.example.gymsaround.Gyms.data.local.GymsDatabase
import com.example.gymsaround.Gyms.data.local.LocalGym
import com.example.gymsaround.Gyms.data.local.LocalGymFavouriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class GymsScreenRepository @Inject constructor(
    private val gymsDao: GymsDao,
    private val apiService: GymsApiService
) {

    suspend fun toggleFavouriteGym(gymId: Int, state: Boolean) =
        withContext(Dispatchers.IO) {
            gymsDao.update(
                LocalGymFavouriteState(
                    id = gymId,
                    isFavourite = state
                )
            )
            return@withContext gymsDao.getAllGyms()
        }

    suspend fun loadGyms() = withContext(Dispatchers.IO) {
        try {
            updateLocalDatabase()
        } catch (e: Exception) {
            if (gymsDao.getAllGyms()
                    .isEmpty()
            ) throw Exception("Something went wrong. No data was found, Try connecting to Internet")
        }
    }

    suspend fun getGyms(): List<Gym> = withContext(Dispatchers.IO) {
        return@withContext gymsDao.getAllGyms().map {
            Gym(
                id = it.id,
                name = it.name,
                address = it.address,
                isOpen = it.isOpen,
                isFavourite = it.isFavourite
            )
        }
    }

    suspend fun updateLocalDatabase() {
        val gyms = apiService.getGyms()

        val favouriteGymsList = gymsDao.getFavouriteGyms()

        gymsDao.addAllGyms(gyms.map {
            LocalGym(
                id = it.id,
                name = it.name,
                address = it.address,
                isOpen = it.isOpen,
            )
        })

        gymsDao.updateAll(favouriteGymsList.map {
            LocalGymFavouriteState(
                id = it.id, true
            )
        })
    }
}