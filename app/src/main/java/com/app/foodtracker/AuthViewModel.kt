package com.app.foodtracker

import android.content.Context
import android.renderscript.Long4
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.foodtracker.database.model.User
import com.app.foodtracker.repository.Repository

class AuthViewModel : ViewModel() {

    fun registerUser(context: Context, user:User):Long{
        return Repository.registerUser(context, user)
    }

    fun loginUser(context: Context,email:String,password:String):LiveData<User>{
        return Repository.loginUser(context,email,password)
    }



}