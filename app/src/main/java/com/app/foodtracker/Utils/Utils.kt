package com.app.foodtracker.Utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.app.foodtracker.database.model.User
import java.util.regex.Pattern

class Utils {
    companion object {

        const val DB_NAME="FoodTracker"

        const val USER_COL_FIRST_NAME = "first_name"
        const val USER_COL_LAST_NAME = "last_name"
        const val USER_COL_EMAIL = "email"
        const val USER_COL_PASSWORD = "password"
        const val USER_COL_PHONE_NUMBER = "phone_number"
        const val USER_COL_ADDRESS = "address"

        const val MEAL_COL_TYPE = "type"
        const val MEAL_COL_DATE = "date"
        const val MEAL_COL_TIME = "time"
        const val MEAL_COL_DESCRIPTION = "description"

        const val STR_BREAKFAST="Breakfast"
        const val STR_LUNCH="Lunch"
        const val STR_DINNER="Dinner"
        const val STR_SNACKS="Snacks"

        val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        val BREAKFAST: Int = 0
        val LUNCH: Int = 1
        val DINNER: Int = 2
        val SNACKS: Int = 3
        fun emailValidation(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun showToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}