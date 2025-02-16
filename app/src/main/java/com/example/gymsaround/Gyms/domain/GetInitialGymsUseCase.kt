package com.example.gymsaround.Gyms.domain

import com.example.gymsaround.Gyms.data.GymsScreenRepository
import javax.inject.Inject

class GetInitialGymsUseCase @Inject constructor(
    private val gymsRepository: GymsScreenRepository,
    val gerSortedGymsUseCase: GetSortedGymsUseCase
) {


    suspend operator fun invoke(): List<Gym> {
        gymsRepository.loadGyms()
        return gerSortedGymsUseCase()
    }
}