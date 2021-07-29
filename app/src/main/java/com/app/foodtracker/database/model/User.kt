package com.app.foodtracker.database.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.foodtracker.Utils.Utils
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Users")
data class User(
    @ColumnInfo(name = Utils.USER_COL_FIRST_NAME) val firstName: String?,
    @ColumnInfo(name = Utils.USER_COL_LAST_NAME) val lastName: String?,
    @PrimaryKey @ColumnInfo(name = Utils.USER_COL_EMAIL) @NonNull val email: String?,
    @ColumnInfo(name = Utils.USER_COL_PASSWORD) val password: String?,
    @ColumnInfo(name = Utils.USER_COL_ADDRESS) val address: String?,
    @ColumnInfo(name = Utils.USER_COL_PHONE_NUMBER) val phoneNumber: String?
)
