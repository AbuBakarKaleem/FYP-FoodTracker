package com.app.foodtracker.database.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.foodtracker.Utils.Utils

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = Utils.USER_COL_FIRST_NAME) val firstName: String?,
    @ColumnInfo(name = Utils.USER_COL_LAST_NAME) val lastName: String?,
    @ColumnInfo(name = Utils.USER_COL_EMAIL) val email: String?,
    @ColumnInfo(name = Utils.USER_COL_PASSWORD) val password: String?,
    @ColumnInfo(name = Utils.USER_COL_ADDRESS) val address: String?,
    @ColumnInfo(name = Utils.USER_COL_PHONE_NUMBER) val phoneNumber: String?
)
