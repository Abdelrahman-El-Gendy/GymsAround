package com.example.gymsaround

data class GymsScreenState(
    var isLoading: Boolean,
    var gyms: List<Gym>,
    var error: String? = null
)
