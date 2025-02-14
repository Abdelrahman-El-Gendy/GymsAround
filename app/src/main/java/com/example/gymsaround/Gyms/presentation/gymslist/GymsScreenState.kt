package com.example.gymsaround.Gyms.presentation.gymslist

import com.example.gymsaround.Gyms.domain.Gym

data class GymsScreenState(
    var isLoading: Boolean,
    var gyms: List<Gym>,
    var error: String? = null
)
