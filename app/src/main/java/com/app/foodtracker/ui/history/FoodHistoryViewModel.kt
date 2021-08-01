package com.app.foodtracker.ui.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.foodtracker.database.model.MealRecord
import com.app.foodtracker.repository.Repository

class FoodHistoryViewModel : ViewModel() {

    fun getMealRecords(context: Context):List<MealRecord>{
        return Repository.getMealRecords(context)
    }
}