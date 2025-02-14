package com.example.gymsaround.Gyms.domain

import com.example.gymsaround.Gyms.data.GymsScreenRepository

class GetInitialGymsUseCase {
    private val gymsRepository = GymsScreenRepository()
    val gerSortedGymsUseCase = GetSortedGymsUseCase()

    suspend operator fun invoke(): List<Gym> {
        gymsRepository.loadGyms()
        return gerSortedGymsUseCase()
    }
}