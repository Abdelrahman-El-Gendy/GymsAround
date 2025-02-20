package com.example.gymsaround

import com.example.gymsaround.Gyms.data.GymsScreenRepository
import com.example.gymsaround.Gyms.data.local.GymsDao
import com.example.gymsaround.Gyms.data.local.LocalGym
import com.example.gymsaround.Gyms.data.local.LocalGymFavouriteState
import com.example.gymsaround.Gyms.data.remote.GymsApiService
import com.example.gymsaround.Gyms.data.remote.RemoteGym
import com.example.gymsaround.Gyms.domain.GetInitialGymsUseCase
import com.example.gymsaround.Gyms.domain.GetSortedGymsUseCase
import com.example.gymsaround.Gyms.domain.ToggleFavouriteStateUseCase
import com.example.gymsaround.Gyms.presentation.gymslist.GymsScreenState
import com.example.gymsaround.Gyms.presentation.gymslist.GymsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GymsViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun loadingState_isSettingCorrectly() = scope.runTest {

        val viewModel = getViewModel()
        val state = viewModel.state.value

        assert(
            state == GymsScreenState(
                isLoading = true,
                gyms = emptyList(),
                error = null
            )
        )
    }


    @Test
    fun loadingContent_isSetCorrectly() = scope.runTest {
        val viewModel = getViewModel()
        advanceUntilIdle()
        val state = viewModel.state.value

        assert(
            state == GymsScreenState(
                isLoading = false,
                gyms = DummyGymsList.getDummyDomainGymsList(),
                error = null
            )
        )
    }

    private fun getViewModel(): GymsViewModel {
        val repository =
            GymsScreenRepository(TestDatabaseDao(),TestApiService(),dispatcher)
        val sortedGymsUseCase = GetSortedGymsUseCase(repository)
        val getInitialGymsUseCase = GetInitialGymsUseCase(repository, sortedGymsUseCase)
        val toggleFavouriteStateUseCase = ToggleFavouriteStateUseCase(repository, sortedGymsUseCase)
        return GymsViewModel(
            getInitialGymsUseCase,
            toggleFavouriteStateUseCase,
            dispatcher
        )
    }
}

class TestApiService : GymsApiService {


    override suspend fun getGyms(): List<RemoteGym> {
        return DummyGymsList.getDummyGymsList()
    }

    override suspend fun getGym(id: Int): Map<String, RemoteGym> {
        TODO("Not yet implemented")
    }

}

class TestDatabaseDao : GymsDao {
    private val gyms = HashMap<Int, LocalGym>()

    override suspend fun getAllGyms(): List<LocalGym> {
        return gyms.values.toList()
    }

    override suspend fun addAllGyms(gyms: List<LocalGym>) {
        gyms.forEach { localGym ->
            this.gyms[localGym.id] = localGym
        }
    }

    override suspend fun update(favouriteState: LocalGymFavouriteState) {
        update(favouriteState)
    }

    override suspend fun getFavouriteGyms(): List<LocalGym> {
        return gyms.values.toList().filter { it.isFavourite }
    }

    override suspend fun updateAll(gymFavouriteState: List<LocalGymFavouriteState>) {
        gymFavouriteState.forEach {
            updateGyms(it)
        }
    }

    private fun updateGyms(state: LocalGymFavouriteState) {
        val gym = this.gyms[state.id]
        gym?.let {
            this.gyms[state.id] = gym.copy(
                id = gym.id,
                isFavourite = gym.isFavourite
            )
        }
    }
}
