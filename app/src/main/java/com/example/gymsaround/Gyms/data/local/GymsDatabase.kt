package com.example.gymsaround.Gyms.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalGym::class],
    version = 3,
    // if we want Room lib to create folder in external storage and save \ put Database scheme
    exportSchema = false
)
abstract class GymsDatabase : RoomDatabase() {

    abstract val dao: GymsDao

}