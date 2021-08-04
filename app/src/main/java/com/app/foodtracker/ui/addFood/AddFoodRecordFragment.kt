package com.app.foodtracker.ui.addFood

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.foodtracker.R
import com.app.foodtracker.Utils.Utils
import com.app.foodtracker.database.model.MealRecord
import com.app.foodtracker.session.SessionManager
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule


class AddFoodRecordFragment : Fragment() {


    private lateinit var viewModel: AddFoodRecordViewModel
    private lateinit var rootView: View
    private lateinit var tv_selectedMealType: TextView
    private lateinit var tv_selectDate: TextView
    private lateinit var tv_selectTime: TextView
    private lateinit var tv_lastMealTaken: TextView
    private lateinit var ll_selectDate: LinearLayout
    private lateinit var ll_selectTime: LinearLayout
    private lateinit var iv_selectDate: ImageView
    private lateinit var iv_selectTime: ImageView
    private lateinit var et_description: EditText
    private lateinit var btn_save: AppCompatButton
    private lateinit var foodType: String

    private val dateSelected: Calendar = Calendar.getInstance()
    private var datePickerDialog: DatePickerDialog? = null

    val navArgs: AddFoodRecordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.add_food_record_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddFoodRecordViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        tv_selectedMealType = rootView.findViewById(R.id.tv_selectedMealType)
        tv_selectDate = rootView.findViewById(R.id.tv_selectDate)
        tv_selectTime = rootView.findViewById(R.id.tv_selectTime)
        ll_selectDate = rootView.findViewById(R.id.ll_selectDate)
        ll_selectTime = rootView.findViewById(R.id.ll_selectTime)
        iv_selectDate = rootView.findViewById(R.id.iv_selectDate)
        iv_selectTime = rootView.findViewById(R.id.iv_selectTime)
        tv_lastMealTaken = rootView.findViewById(R.id.tv_lastMealTaken)
        et_description = rootView.findViewById(R.id.et_description)
        btn_save = rootView.findViewById(R.id.btn_save)

        btn_save.setOnClickListener { validation() }

        /* ll_selectDate.setOnClickListener { showCalender() }
         tv_selectDate.setOnClickListener { showCalender() }

         ll_selectTime.setOnClickListener { showClock() }
         tv_selectTime.setOnClickListener { showClock() }
 */
        setMealTypeOnUI()
        setDateAndTimeToUI()

    }

    @SuppressLint("SetTextI18n")
    private fun setMealTypeOnUI() {
        val sessionManager = SessionManager(rootView.context)

        when (navArgs.foodType) {
            0 -> {
                foodType = Utils.STR_BREAKFAST
                val isBreakfastTaken = sessionManager.getLastBreakFast()
                if (isBreakfastTaken.isNullOrEmpty().not() && isBreakfastTaken.equals(
                        getCurrentDateTime().split(" ")[0]
                    )
                ) {
                    hideAddRecordButton(foodType)
                }
            }
            1 -> {
                foodType = Utils.STR_LUNCH
                val isLunchTaken = sessionManager.getLastLunch()
                if (isLunchTaken.isNullOrEmpty()
                        .not() && isLunchTaken.equals(getCurrentDateTime().split(" ")[0])
                ) {
                    hideAddRecordButton(foodType)
                }
            }
            2 -> {
                foodType = Utils.STR_DINNER
                val isDinnerTaken = sessionManager.getLastDinner()
                if (isDinnerTaken.isNullOrEmpty()
                        .not() && isDinnerTaken.equals(getCurrentDateTime().split(" ")[0])
                ) {
                    hideAddRecordButton(foodType)
                }
            }
            3 -> {
                foodType = Utils.STR_SNACKS
            }
            else -> {
                foodType = ""
            }
        }
        tv_selectedMealType.text = getString(R.string.select_meal) + foodType
    }

    private fun setMealTakenValue(value: String) {
        try {
            val sessionManager = SessionManager(rootView.context)
            when (value) {
                Utils.STR_BREAKFAST -> {
                    sessionManager.setLastBreakfast(getCurrentDateTime().split(" ")[0])
                }
                Utils.STR_LUNCH -> {
                    sessionManager.setLastLunch(getCurrentDateTime().split(" ")[0])
                }
                Utils.STR_DINNER -> {
                    sessionManager.setLastDinner(getCurrentDateTime().split(" ")[0])
                }
                else -> {
                    return
                }
            }
        } catch (e: java.lang.Exception) {
            Log.e("App", e.message.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDateAndTimeToUI() {
        val dateTime = getCurrentDateTime()
        tv_selectDate.text = dateTime.split(" ")[0]
        tv_selectTime.text = dateTime.split(" ")[1] + " " + dateTime.split(" ")[2]
    }

    private fun validation() {
        if (tv_selectedMealType.text.isEmpty()) {
            Utils.showToast(rootView.context, "Meal Type is Required")
            return

        }
        if (tv_selectDate.text.toString().contentEquals(getString(R.string.select_date))) {
            Utils.showToast(rootView.context, "please select date")
            return

        }
        if (tv_selectTime.text.toString().contentEquals(getString(R.string.select_time))) {
            Utils.showToast(rootView.context, "please select time")
            return

        }
        if (et_description.text.isEmpty()) {
            et_description.error = getString(R.string.required)
            return

        }
        saveMealInfoIntoDatabase()

    }

    private fun saveMealInfoIntoDatabase() {
        try {
            val mealRecord = MealRecord(
                foodType.trim(),
                tv_selectDate.text.toString().trim(),
                tv_selectTime.text.toString().trim(),
                et_description.text.toString().trim()
            )
            val recordInsertStatus = viewModel.insertMealRecord(rootView.context, mealRecord)
            if (recordInsertStatus > 0) {
                showToast(getString(R.string.meal_insert_message))
                setMealTakenValue(foodType.trim())
                goBack()

            } else {
                showToast(getString(R.string.meal_insert_fail_message))
            }

        } catch (e: Exception) {
            Log.e("App", e.message.toString())
            Utils.showToast(rootView.context, getString(R.string.something_went_wrong))
        }
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

    private fun showCalender() {

        val newCalendar = Calendar.getInstance()
        val StartTime = DatePickerDialog(
            rootView.context, R.style.DatePickerDialogTheme,
            { view, year, monthOfYear, dayOfMonth ->

                val calendar = Calendar.getInstance()
                calendar[year, monthOfYear] = dayOfMonth
                val dateFormatForSelectedRouteInfo = SimpleDateFormat("dd-MM-yyyy")
                val dateForSelectedRouteInfo: String = dateFormatForSelectedRouteInfo.format(
                    calendar.time
                )
                tv_selectDate.text = dateForSelectedRouteInfo
            }, newCalendar[Calendar.YEAR], newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        StartTime.show()
        val calendar = Calendar.getInstance()
        StartTime.datePicker.minDate = calendar.timeInMillis - 1000
        StartTime.getButton(DatePickerDialog.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(rootView.context, R.color.teal_200))
        StartTime.getButton(DatePickerDialog.BUTTON_NEGATIVE)
            .setTextColor(ContextCompat.getColor(rootView.context, R.color.teal_700))
    }

    private fun showClock() {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        val mTimePicker: TimePickerDialog = TimePickerDialog(
            rootView.context, R.style.DatePickerDialogTheme,
            { timePicker, selectedHour, selectedMinute ->
                tv_selectTime.text = "$selectedHour:$selectedMinute"
            },
            hour,
            minute,
            false
        ) //Yes 24 hour time

        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
        mTimePicker.getButton(TimePickerDialog.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(rootView.context, R.color.teal_200))
        mTimePicker.getButton(TimePickerDialog.BUTTON_NEGATIVE)
            .setTextColor(ContextCompat.getColor(rootView.context, R.color.teal_700))
    }

    private fun showToast(message: String) {
        Utils.showToast(rootView.context, message)
    }

    private fun getCurrentDateTime(): String {
        var currentDateTime = ""
        try {
            val df: DateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss a")
            currentDateTime = df.format(Calendar.getInstance().time)
            Log.e("App", "Current date =========>>$currentDateTime")
        } catch (e: Exception) {
            Log.e("App", e.message.toString())
        }
        return currentDateTime

    }

    @SuppressLint("SetTextI18n")
    private fun hideAddRecordButton(value: String) {
        tv_lastMealTaken.text = "Today's $foodType is already done"
        btn_save.visibility = View.GONE
        tv_lastMealTaken.visibility = View.VISIBLE
    }

}