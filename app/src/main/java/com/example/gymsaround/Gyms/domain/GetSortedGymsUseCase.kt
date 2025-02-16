package com.example.gymsaround.Gyms.domain

import com.example.gymsaround.Gyms.data.GymsScreenRepository
import javax.inject.Inject

class GetSortedGymsUseCase @Inject constructor(
    private val gymsRepository: GymsScreenRepository
) {


    suspend operator fun invoke(): List<Gym> {
        return gymsRepository.getGyms().sortedBy { it.name }
    }
}