package com.app.foodtracker.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.foodtracker.R
import com.app.foodtracker.database.model.MealRecord
import java.util.ArrayList

class MealHistoryRecyclerViewAdapter(mealRecordList: ArrayList<MealRecord>) :
    RecyclerView.Adapter<MealHistoryRecyclerViewAdapter.ViewHolder>() {

    var mealList: ArrayList<MealRecord> = ArrayList<MealRecord>()

    init {
        mealList = mealRecordList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_list_item, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            var mealData = mealList[position]
            holder.tv_itemMealType?.text = mealData.mealType
            holder.tv_itemMealDateTime?.text = mealData.mealDate + " " + mealData.mealTime
        } catch (e: Exception) {
            Log.e("App", e.message.toString())
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_itemMealDateTime: AppCompatTextView? = null
        var tv_itemMealType: AppCompatTextView? = null

        init {
            try {
                tv_itemMealType = itemView.findViewById(R.id.tv_itemMealType)
                tv_itemMealDateTime = itemView.findViewById(R.id.tv_itemMealDateTime)
            } catch (e: Exception) {
                Toast.makeText(
                    itemView.context,
                    "Error in View Holder " + e.message,
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }
}