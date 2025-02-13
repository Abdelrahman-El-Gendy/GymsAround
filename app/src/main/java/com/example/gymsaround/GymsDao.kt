package com.example.gymsaround

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GymsDao {

    @Query("SELECT * FROM gyms")
    suspend fun getAllGyms(): List<Gym>

    // cuz of the primary key(id) annotated -->> must be unique
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllGyms(gyms: List<Gym>)

    @Update(entity = Gym::class)
    suspend fun update(favouriteState: GymFavouriteState)

    @Query("SELECT * FROM gyms WHERE is_favourite = 1")
    suspend fun getFavouriteGyms(): List<Gym>

    @Update(entity = Gym::class)
    suspend fun updateAll(gymFavouriteState: List<GymFavouriteState>)

}