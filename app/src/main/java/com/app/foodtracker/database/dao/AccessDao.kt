package com.app.foodtracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.foodtracker.database.model.MealRecord
import com.app.foodtracker.database.model.User

@Dao
interface AccessDao {

    @Insert
    fun registerUser(vararg users: User):String

    @Query("SELECT * FROM User WHERE email=:email")
    fun isEmailExist(email:String):String

    @Query("SELECT * FROM User WHERE email=:email AND password=:password")
    fun loginUser(email:String,password:String):LiveData<User>

    @Insert
    fun insertMealRecord(vararg record: MealRecord):String

    @Query("SELECT * FROM MealRecord")
    fun getAllMealRecords(): MealRecord





}