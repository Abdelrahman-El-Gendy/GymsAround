package com.example.gymsaround.Gyms.domain

import com.example.gymsaround.Gyms.data.GymsScreenRepository

class ToggleFavouriteStateUseCase {
    private val gymsRepository = GymsScreenRepository()
    private val sortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke(gymId: Int, oldValue: Boolean): List<Gym> {
        val newState = oldValue.not()
        gymsRepository.toggleFavouriteGym(gymId, newState).sortedBy { it.name }
        return sortedGymsUseCase()
    }
}