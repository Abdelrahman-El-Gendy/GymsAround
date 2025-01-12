package com.example.gymsaround

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GymsViewModel : ViewModel() {

    var state by mutableStateOf(getGyms())

    private fun getGyms() = listOfGyms

    fun toggleFavouriteState(gymId: Int) {
        val gyms = state.toMutableList()
        val indexItem = gyms.indexOfFirst { it.id == gymId }
        gyms[indexItem] = gyms[indexItem].copy(isFavourite = !gyms[indexItem].isFavourite)
        state = gyms
    }

}