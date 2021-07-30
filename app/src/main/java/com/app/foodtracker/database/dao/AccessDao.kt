package com.app.foodtracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.foodtracker.database.model.MealRecord
import com.app.foodtracker.database.model.User

@Dao
interface AccessDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun registerUser(users: User):Long

    @Query("SELECT * FROM Users WHERE email=:email")
    fun isEmailExist(email:String):LiveData<User>

    @Query("SELECT * FROM Users WHERE email=:email AND password=:password")
    fun loginUser(email:String,password:String):User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMealRecord(record: MealRecord):Long

   /* @Query("SELECT * FROM MealRecord")
    fun getAllMealRecords(): MealRecord*/





}