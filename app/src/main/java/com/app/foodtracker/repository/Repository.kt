package com.app.foodtracker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.app.foodtracker.database.DatabaseInstance
import com.app.foodtracker.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository {

    companion object{
        var databaseInstance: DatabaseInstance? = null

        //var loginTableModel: LiveData<LoginTableModel>? = null

        fun initializeDB(context: Context) : DatabaseInstance {
            return DatabaseInstance.getDatabaseClient(context)
        }

        fun registerUser(context: Context, user:User):Long {

            databaseInstance = initializeDB(context)
            return databaseInstance!!.accessDao().registerUser(user)

        }
        fun loginUser(context: Context, email: String,password:String):LiveData<User>{
            databaseInstance = initializeDB(context)
            return databaseInstance!!.accessDao().loginUser(email,password)
        }
        fun isEmailExist(context: Context,email:String): LiveData<User> {
            databaseInstance = initializeDB(context)
            return this.databaseInstance!!.accessDao().isEmailExist(email)
        }

    }
}