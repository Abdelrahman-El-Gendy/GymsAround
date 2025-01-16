package com.example.gymsaround

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsViewModel(
    private val stateHandled: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(emptyList<Gym>())
    private lateinit var apiService: GymsApiService
    private lateinit var gymCall: Call<List<Gym>>

    init {
        // 1- Create Retrofit Builder
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://gyms-295f1-default-rtdb.firebaseio.com/")
            .build()
        //2- Integrating Builder and GymsApiServices Interface...
        apiService = retrofit.create(GymsApiService::class.java)

        getGyms()
    }

    private fun getGyms() {
        gymCall = apiService.getGyms()
        gymCall.enqueue(object : Callback<List<Gym>> {
            override fun onResponse(call: Call<List<Gym>>, response: Response<List<Gym>>) {
                response.body()?.let {
                    state = it.restoreSelectedGym()
                }
            }

            override fun onFailure(call: Call<List<Gym>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        gymCall.cancel()
    }

    fun toggleFavouriteState(gymId: Int) {
        val gyms = state.toMutableList()
        val indexItem = gyms.indexOfFirst { it.id == gymId }
        gyms[indexItem] = gyms[indexItem].copy(isFavourite = !gyms[indexItem].isFavourite)
        storeSelectedGym(gyms[indexItem])
        state = gyms
    }

    private fun storeSelectedGym(gym: Gym) {
        val stateHandledList = stateHandled.get<List<Int>>(FAV_IDS).orEmpty().toMutableList()
        if (gym.isFavourite) stateHandledList.add(gym.id) else stateHandledList.remove(gym.id)
        stateHandled[FAV_IDS] = stateHandledList

    }

    private fun List<Gym>.restoreSelectedGym(): List<Gym> {
        // val gyms = this
        stateHandled.get<List<Int?>>(FAV_IDS).let { savedIds ->
            savedIds?.forEach { gymId ->
                this.find { it.id == gymId }?.isFavourite = true
            }
        }
        return this
    }

    companion object {
        const val FAV_IDS = "favouriteGymIds"
    }
}
