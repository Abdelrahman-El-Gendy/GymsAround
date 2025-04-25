package com.example.gymsaround

import com.example.gymsaround.Gyms.data.GymsScreenRepository
import com.example.gymsaround.Gyms.domain.GetSortedGymsUseCase
import com.example.gymsaround.Gyms.domain.ToggleFavouriteStateUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ToggleFavouriteStateUseCaseTest {

    private val dispatchers = StandardTestDispatcher()
    private val scope = TestScope(dispatchers)

    @Test
    fun toggleFavouriteState_updateFavouriteProperty() = scope.runTest {
        //Setting Up
        val gymsRepo = GymsScreenRepository(TestGymsDao(), TestGymsApiService(), dispatchers)
        val getSortedGymsUseCase = GetSortedGymsUseCase(gymsRepo)
        val useCaseUnderTest = ToggleFavouriteStateUseCase(gymsRepo, getSortedGymsUseCase)

        gymsRepo.loadGyms()
        advanceUntilIdle() // identifies that there are not remaining Tasks.


        val gyms = DummyGymsList.getDummyDomainGymsList()
        val gymsUngerTest = gyms[0]
        val isFav = gymsUngerTest.isFavourite

        val updatedGymsList = useCaseUnderTest(gymsUngerTest.id, isFav)
        advanceUntilIdle()


        //Assertion
        assert(updatedGymsList[0].isFavourite == !isFav)
    }

}