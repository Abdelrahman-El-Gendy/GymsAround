package com.example.gymsaround.Gyms.presentation.gymslist

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymsaround.Gyms.data.di.MainDispatcher
import com.example.gymsaround.Gyms.domain.GetInitialGymsUseCase
import com.example.gymsaround.Gyms.domain.ToggleFavouriteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GymsViewModel @Inject constructor(
    private val getInitialGymsUseCase: GetInitialGymsUseCase,
    private val toggleFavouriteStateUseCase: ToggleFavouriteStateUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {


    private var _state by mutableStateOf(
        GymsScreenState(
            gyms = emptyList(),
            isLoading = true,
        )
    )
    val state: State<GymsScreenState> get() = derivedStateOf { _state }

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()

        _state = _state.copy(
            isLoading = false,
            error = throwable.message
        )
    }

    init {

        getGyms()

    }

    private fun getGyms() {
        viewModelScope.launch(errorHandler + dispatcher) {
            val receivedGyms = getInitialGymsUseCase()
            _state = _state.copy(
                gyms = receivedGyms,
                isLoading = false
            )
        }
    }


    fun toggleFavouriteState(gymId: Int, oldState: Boolean) {
//        val gyms = _state.gyms.toMutableList()
//        val indexItem = gyms.indexOfFirst { it.id == gymId }

        viewModelScope.launch(errorHandler + dispatcher) {
            //  val updatedGymsList = repo.toggleFavouriteGym(gymId, !gyms[indexItem].isFavourite)
            // val updatedGymsList = toggleFavouriteStateUseCase(gymId, gyms[indexItem].isFavourite)
            val updatedGymsList = toggleFavouriteStateUseCase(gymId, oldState)
            _state = _state.copy(
                gyms = updatedGymsList
            )
        }
    }


    /**
    //instead of creating a new Map , We can convert the current list into a Map,
    // Copy Function -->> is a function in Kotlin data classes that allows creating new instance while modifying only certain field.
    private fun List<Gym>.restoreSelectedGym(): List<Gym> {

    val savedId = stateHandled.get<List<Int>?>(FAV_IDS) ?: return this
    return this.map { gym ->
    if (gym.id in savedId) gym.copy(isFavourite = true) else gym
    }
    }
     **/

}
