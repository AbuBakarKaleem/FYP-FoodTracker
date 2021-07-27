package com.app.foodtracker.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.foodtracker.database.Model.User

@Dao
interface UserDao {
    @Insert
    fun registerUser(vararg users: User):String

    @Query("SELECT * FROM User WHERE email=:email")
    fun isEmailExist(email:String):User

    @Query("SELECT * FROM User WHERE email=:email AND password=:password")
    fun loginUser(email:String,password:String):User





}