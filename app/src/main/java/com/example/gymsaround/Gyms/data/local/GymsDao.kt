package com.example.gymsaround.Gyms.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GymsDao {

    @Query("SELECT * FROM gyms")
    suspend fun getAllGyms(): List<LocalGym>

    // cuz of the primary key(id) annotated -->> must be unique
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllGyms(gyms: List<LocalGym>)

    @Update(entity = LocalGym::class)
    suspend fun update(favouriteState: LocalGymFavouriteState)

    @Query("SELECT * FROM gyms WHERE is_favourite = 1")
    suspend fun getFavouriteGyms(): List<LocalGym>

    @Update(entity = LocalGym::class)
    suspend fun updateAll(gymFavouriteState: List<LocalGymFavouriteState>)

}