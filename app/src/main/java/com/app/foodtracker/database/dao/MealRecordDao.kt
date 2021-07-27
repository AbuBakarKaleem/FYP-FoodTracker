package com.app.foodtracker.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.app.foodtracker.database.Model.MealRecord
import com.app.foodtracker.database.Model.User

interface MealRecordDao {

    @Insert
    fun insertMealRecord(vararg record: MealRecord):String

    @Query("SELECT * FROM MealRecord")
    fun getAllMealRecords():MealRecord

}