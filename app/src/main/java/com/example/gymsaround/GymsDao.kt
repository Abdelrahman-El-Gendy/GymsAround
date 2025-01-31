package com.example.gymsaround

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GymsDao {

    @Query("SELECT * FROM gyms")
    suspend fun getAllGyms(): List<Gym>

    // cuz of the primary key(id) annotated -->> must be unique
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllGyms(): List<Gym>
}