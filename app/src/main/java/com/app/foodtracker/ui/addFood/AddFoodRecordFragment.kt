package com.app.foodtracker.ui.addFood

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.app.foodtracker.R
import java.util.*


class AddFoodRecordFragment : Fragment() {


    private lateinit var viewModel: AddFoodRecordViewModel
    private lateinit var rootView: View
    private lateinit var tv_selectedMealType:TextView
    private lateinit var tv_selectDate:TextView
    private lateinit var tv_selectTime:TextView
    private lateinit var ll_selectDate:LinearLayout
    private lateinit var ll_selectTime:LinearLayout
    private lateinit var iv_selectDate:ImageView
    private lateinit var iv_selectTime:ImageView
    private lateinit var et_description:EditText
    private lateinit var btn_save:AppCompatButton

    private val dateSelected: Calendar = Calendar.getInstance()
    private var datePickerDialog: DatePickerDialog? = null

    val navArgs: AddFoodRecordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView=inflater.inflate(R.layout.add_food_record_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddFoodRecordViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val tv_value= rootView.findViewById<TextView>(R.id.tv_value)
        tv_value.text=navArgs.foodType.toString()*/

        init()

    }
    private fun init(){

        tv_selectedMealType=rootView.findViewById(R.id.tv_selectedMealType)
        tv_selectDate=rootView.findViewById(R.id.tv_selectDate)
        tv_selectTime=rootView.findViewById(R.id.tv_selectTime)
        ll_selectDate=rootView.findViewById(R.id.ll_selectDate)
        ll_selectTime=rootView.findViewById(R.id.ll_selectTime)
        iv_selectDate=rootView.findViewById(R.id.iv_selectDate)
        iv_selectTime=rootView.findViewById(R.id.iv_selectTime)
        et_description=rootView.findViewById(R.id.et_description)
        btn_save=rootView.findViewById(R.id.btn_save)

        btn_save.setOnClickListener { saveInfo() }

        ll_selectDate.setOnClickListener { setDate() }

        tv_selectDate.setOnClickListener { setDate() }

        setMealTypeOnUI()

    }
    private fun setMealTypeOnUI(){
        when(navArgs.foodType){
            0 -> {
                tv_selectedMealType.text = "Breakfast"
            }
            1 -> {
                tv_selectedMealType.text = "Lunch"
            }
            2 -> {
                tv_selectedMealType.text = "Dinner"
            }
            3 -> {
                tv_selectedMealType.text = "Snacks"
            }
            else->{
                tv_selectedMealType.text=""
            }
        }
    }

    private fun saveInfo(){

    }

    private fun setDate() {
        var newCalendar: Calendar = dateSelected
        datePickerDialog = DatePickerDialog(
            rootView.context,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0)
                //dateEditText.setText(dateFormatter.format(dateSelected.getTime()))
                tv_selectDate.text= dateSelected.toString()
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
       // dateEditText.setText(dateFormatter.format(dateSelected.getTime()))
    }

}