package com.app.foodtracker.Utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.util.regex.Pattern

class Utils {
    companion object{
        val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        val BREAKFAST:Int=0
        val LUNCH:Int=1
        val DINNER:Int=2
        val SNACKS:Int=3
        fun emailValidation(email: String):Boolean{
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        fun showToast(context: Context, text:String){
            Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
        }
    }
}