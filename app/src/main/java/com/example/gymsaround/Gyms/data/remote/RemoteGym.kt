package com.example.gymsaround.Gyms.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteGym(
    val id: Int,

    @SerializedName("gym_name")
    val name: String,

    @SerializedName("gym_location")
    val address: String,

    @SerializedName("is_open")
    val isOpen: Boolean,

    )
