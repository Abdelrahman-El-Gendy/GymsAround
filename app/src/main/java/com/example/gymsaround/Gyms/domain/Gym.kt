package com.example.gymsaround.Gyms.domain

data class Gym(
    val id: Int,
    val name: String,
    val address: String,
    val isOpen: Boolean,
    val isFavourite: Boolean = false
)
