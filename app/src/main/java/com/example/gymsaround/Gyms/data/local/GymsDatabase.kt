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

    companion object {

        // volatile -->> that makes this variable accessible through other classes
        @Volatile
        private var daoInstance: GymsDao? = null

        private fun buildDatabase(context: Context): GymsDatabase = Room.databaseBuilder(
            context.applicationContext,
            GymsDatabase::class.java,
            "gyms_database"
        ).fallbackToDestructiveMigration().build()


        // in order to only make one reference \ instance \ call for method buildDatabase -->>
        // we use synchronized(this){} block.
        // Every Database created has an address in memory and
        // synchronized prevent two address for the same Database
        fun getDaoInstance(context: Context): GymsDao {
            synchronized(this) {
                if (daoInstance == null) {
                    daoInstance = buildDatabase(context).dao
                }
                return daoInstance as GymsDao
            }
        }
    }
}