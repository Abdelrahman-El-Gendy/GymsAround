package com.example.gymsaround

import com.google.gson.annotations.SerializedName

data class Gym(
    var id: Int,
    @SerializedName("gym_name")
    val name: String,
    @SerializedName("gym_location")
    val address: String,
    var isFavourite: Boolean = false
)
