package com.app.foodtracker.ui.addFood

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
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
import androidx.navigation.fragment.navArgs
import com.app.foodtracker.R
import com.app.foodtracker.Utils.Utils
import java.text.SimpleDateFormat
import java.util.*


class AddFoodRecordFragment : Fragment() {


    private lateinit var viewModel: AddFoodRecordViewModel
    private lateinit var rootView: View
    private lateinit var tv_selectedMealType: TextView
    private lateinit var tv_selectDate: TextView
    private lateinit var tv_selectTime: TextView
    private lateinit var ll_selectDate: LinearLayout
    private lateinit var ll_selectTime: LinearLayout
    private lateinit var iv_selectDate: ImageView
    private lateinit var iv_selectTime: ImageView
    private lateinit var et_description: EditText
    private lateinit var btn_save: AppCompatButton

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
        et_description = rootView.findViewById(R.id.et_description)
        btn_save = rootView.findViewById(R.id.btn_save)

        btn_save.setOnClickListener { validation() }

        ll_selectDate.setOnClickListener { showCalender() }
        tv_selectDate.setOnClickListener { showCalender() }

        ll_selectTime.setOnClickListener { showClock() }
        tv_selectTime.setOnClickListener { showClock() }

        setMealTypeOnUI()

    }

    private fun setMealTypeOnUI() {

        when (navArgs.foodType) {
            0 -> {
                tv_selectedMealType.text = "Selected Meal: Breakfast"
            }
            1 -> {
                tv_selectedMealType.text = "Selected Meal: Lunch"
            }
            2 -> {
                tv_selectedMealType.text = "Selected Meal: Dinner"
            }
            3 -> {
                tv_selectedMealType.text = "Selected Meal: Snacks"
            }
            else -> {
                tv_selectedMealType.text = ""
            }
        }
    }

    private fun validation() {
        if(tv_selectedMealType.text.isEmpty()){
            Utils.showToast(rootView.context,"Meal Type is Required")
            return

        }
        if(tv_selectDate.text.toString().contentEquals(getString(R.string.select_date))){
            Utils.showToast(rootView.context,"please select date")
            return

        }
        if(tv_selectTime.text.toString().contentEquals(getString(R.string.select_time))){
            Utils.showToast(rootView.context,"please select time")
            return

        }
        if(et_description.text.isEmpty()){
           et_description.error=getString(R.string.required)
            return

        }
        saveMealInfoIntoDatabase()

    }

    private fun saveMealInfoIntoDatabase() {

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

}