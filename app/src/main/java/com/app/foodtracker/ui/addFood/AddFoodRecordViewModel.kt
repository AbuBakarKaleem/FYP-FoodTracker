package com.app.foodtracker.ui.addFood

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.foodtracker.database.model.MealRecord
import com.app.foodtracker.repository.Repository
import java.security.AccessControlContext

class AddFoodRecordViewModel : ViewModel() {

    fun insertMealRecord(context:Context, mealRecord: MealRecord):Long{
        return Repository.insertMealRecord(context,mealRecord)
    }
}