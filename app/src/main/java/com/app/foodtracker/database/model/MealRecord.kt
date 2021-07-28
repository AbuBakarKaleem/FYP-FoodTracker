package com.app.foodtracker.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.foodtracker.Utils.Utils

@Entity
data class MealRecord(

    @PrimaryKey(autoGenerate = true) val recordID: Int,
    @ColumnInfo(name = Utils.MEAL_COL_TYPE) val mealType: String?,
    @ColumnInfo(name = Utils.MEAL_COL_DATE) val mealDate: String?,
    @ColumnInfo(name = Utils.MEAL_COL_TIME) val mealTime: String?,
    @ColumnInfo(name = Utils.MEAL_COL_DESCRIPTION) val mealDescription: String?,

)
