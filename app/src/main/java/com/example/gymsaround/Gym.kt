package com.example.gymsaround

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gyms")
data class Gym(

    @PrimaryKey
    @ColumnInfo("gym_id")
    val id: Int,

    @ColumnInfo("gym_name")
    @SerializedName("gym_name")
    val name: String,

    @ColumnInfo("gym_location")
    @SerializedName("gym_location")
    val address: String,

    @SerializedName("is_open")
    val isOpen: Boolean,

    @ColumnInfo("is_favourite")
    val isFavourite: Boolean = false
)
