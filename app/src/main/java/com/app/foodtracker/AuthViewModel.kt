package com.app.foodtracker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.foodtracker.database.model.User
import com.app.foodtracker.repository.Repository

class AuthViewModel : ViewModel() {

    var liveDataLogin: LiveData<User>? = null

    fun registerUser(context: Context, user:User):LiveData<String>{
        var isEmailExist=Repository.isEmailExist(context,user.email.toString())
        return if(isEmailExist.isNullOrEmpty().not())
            Repository.registerUser(context, user).toString() as LiveData<String>
        else
            "User Already Exist" as LiveData<String>
    }

    fun loginUser(context: Context,email:String,password:String):LiveData<User>{
        liveDataLogin= Repository.loginUser(context,email,password)
        return liveDataLogin as LiveData<User>
    }



}