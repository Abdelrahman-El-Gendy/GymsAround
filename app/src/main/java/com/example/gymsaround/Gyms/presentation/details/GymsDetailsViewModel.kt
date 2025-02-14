package com.example.gymsaround.Gyms.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymsaround.Gyms.data.GymsDetailsRepository
import com.example.gymsaround.Gyms.domain.Gym
import kotlinx.coroutines.launch

class GymsDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val state = mutableStateOf<Gym?>(null)


    val repo: GymsDetailsRepository = GymsDetailsRepository()

    init {
        val gymId = savedStateHandle.get<Int>("gym_id") ?: 0
        getGym(gymId)
    }

    private fun getGym(id: Int) {
        viewModelScope.launch {
            val gym = repo.getGymFromRemoteDB(id)
            state.value = gym
        }
    }

}