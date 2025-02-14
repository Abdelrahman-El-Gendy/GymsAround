package com.example.gymsaround.Gyms.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gyms")
data class LocalGym(

    @PrimaryKey
    @ColumnInfo("gym_id")
    val id: Int,

    @ColumnInfo("gym_name")
    val name: String,

    @ColumnInfo("gym_location")
    val address: String,

    @ColumnInfo("is_open")
    val isOpen: Boolean,

    @ColumnInfo("is_favourite")
    val isFavourite: Boolean = false
)
