package com.example.gymsaround.Gyms.domain

import com.example.gymsaround.Gyms.data.GymsScreenRepository

class GetSortedGymsUseCase {
    private val gymsRepository = GymsScreenRepository()

    suspend operator fun invoke(): List<Gym> {
        return gymsRepository.getGyms().sortedBy { it.name }
    }
}