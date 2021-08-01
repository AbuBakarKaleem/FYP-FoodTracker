package com.app.foodtracker.session

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.app.foodtracker.HomeActivity
import com.app.foodtracker.LoginActivity
import com.app.foodtracker.database.model.User
import com.google.gson.Gson

class SessionManager(context: Context) {

    var pref: SharedPreferences? = null

    // Editor for Shared preferences
    var editor: Editor? = null

    // Context
    var _context: Context? = null

    // Shared pref mode
    var PRIVATE_MODE = 0
    val KEY_USER = "user_info"

    private val PREF_NAME = "FoodTracker Preference"

    // All Shared Preferences Keys
    private val IS_LOGIN = "IsLoggedIn"

    init {
        this._context=context
        pref = _context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref?.edit()
    }

    fun createLoginSession(userInfo: User) {
        editor!!.putBoolean(IS_LOGIN, true)
        editor!!.putString(KEY_USER, objectToString(userInfo))
        editor!!.commit()
    }
    fun getUserDetails():User?{
        return pref?.getString(KEY_USER, null)?.let { stringToObject(it) }
    }
    public fun checkLogin(){
        if (this.isLoggedIn()) {

            val i = Intent(_context, HomeActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            _context!!.startActivity(i)
        }
    }
    private fun objectToString(userInfo: User):String{
        val gson = Gson()
        return gson.toJson(userInfo)
    }
    private fun stringToObject(value: String):User{
        val gson = Gson()
        return gson.fromJson(value, User::class.java)
    }
    private fun isLoggedIn(): Boolean {
        return pref!!.getBoolean(IS_LOGIN, false)
    }
    public fun logoutUser(){
        editor!!.clear()
        editor!!.commit()
        val i = Intent(_context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        _context!!.startActivity(i)

    }
}