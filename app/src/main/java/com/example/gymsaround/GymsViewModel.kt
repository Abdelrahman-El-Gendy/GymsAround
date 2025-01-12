package com.example.gymsaround

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class GymsViewModel(
    private val stateHandled: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(restoreSelectedGym())

    private fun getGyms() = listOfGyms

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

    private fun restoreSelectedGym(): List<Gym> {
        val gyms = getGyms()
        stateHandled.get<List<Int?>>(FAV_IDS).let { savedIds ->
            savedIds?.forEach { gymId ->
                gyms.find { it.id == gymId }?.isFavourite = true
            }
        }
        return gyms
    }

    companion object {
        const val FAV_IDS = "favouriteGymIds"
    }
}
