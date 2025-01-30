package com.example.gymsaround

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val state = mutableStateOf<Gym?>(null)
    private val gymsApiService: GymsApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gyms-295f1-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gymsApiService = retrofit.create(GymsApiService::class.java)
        val gymId = savedStateHandle.get<Int>("gym_id") ?: 0
        getGym(gymId)
    }

    private fun getGym(id: Int) {
        viewModelScope.launch {
            val gym = getGymFromRemoteDB(id)
            state.value = gym
        }
    }

    private suspend fun getGymFromRemoteDB(id: Int) = withContext(Dispatchers.IO) {
        gymsApiService.getGym(id).values.first()
    }


}