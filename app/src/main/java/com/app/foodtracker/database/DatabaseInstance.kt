package com.app.foodtracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.foodtracker.database.dao.AccessDao
import com.app.foodtracker.database.model.MealRecord
import com.app.foodtracker.database.model.User

@Database(entities = [User::class, MealRecord::class], version = 1, exportSchema = false)
abstract class DatabaseInstance: RoomDatabase() {

    abstract fun accessDao() : AccessDao

    companion object {

        @Volatile
        private var INSTANCE: DatabaseInstance? = null

        fun getDatabaseClient(context: Context) : DatabaseInstance {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, DatabaseInstance::class.java, "FOOD TRACKER")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}