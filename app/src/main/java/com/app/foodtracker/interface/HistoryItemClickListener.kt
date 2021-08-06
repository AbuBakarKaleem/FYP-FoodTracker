package com.app.foodtracker.`interface`

import com.app.foodtracker.database.model.MealRecord

interface HistoryItemClickListener {

    fun onItemClickListener(mealRecord: MealRecord)
}