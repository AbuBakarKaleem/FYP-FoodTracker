package com.app.foodtracker.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.foodtracker.R
import com.app.foodtracker.adapters.MealHistoryRecyclerViewAdapter
import com.app.foodtracker.database.model.MealRecord
import com.opencsv.CSVWriter
import java.io.FileWriter

class FoodHistoryFragment : Fragment() {

    private lateinit var tv_noDataFound:AppCompatTextView
    private lateinit var btn_share:AppCompatButton
    private lateinit var rc_mealHistory:RecyclerView
    private lateinit var viewModel: FoodHistoryViewModel
    private lateinit var rootView:View
    private lateinit var historyList:ArrayList<MealRecord>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView=inflater.inflate(R.layout.food_history_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

         historyList=viewModel.getMealRecords(rootView.context) as ArrayList<MealRecord>
        if(historyList.size>0){
            populateRecyclerView(historyList)

        }else{
            hideShareButtonAndRecyclerView()
        }
    }
    private fun init(){
        viewModel = ViewModelProvider(this).get(FoodHistoryViewModel::class.java)
        btn_share=rootView.findViewById(R.id.btn_share)
        tv_noDataFound=rootView.findViewById(R.id.tv_noDataFound)
        rc_mealHistory=rootView.findViewById(R.id.rc_mealHistory)

        btn_share.setOnClickListener {
            listToCSV()
        }
    }
    private fun hideShareButtonAndRecyclerView(){
        btn_share.visibility=View.GONE
        rc_mealHistory.visibility=View.GONE
        tv_noDataFound.visibility=View.VISIBLE
    }
    private fun showShareButtonAndRecyclerView(){
        btn_share.visibility=View.VISIBLE
        rc_mealHistory.visibility=View.VISIBLE
        tv_noDataFound.visibility=View.GONE
    }
    private fun observeViewModel(){
       /* viewModel.mealLiveData.observe(requireActivity()){
            val recordList=it as ArrayList<MealRecord>
            if(recordList.size>0){
                populateRecyclerView(recordList)

            }else{
                hideShareButtonAndRecyclerView()
            }
        }*/
    }
    private fun populateRecyclerView(recordList:ArrayList<MealRecord>){

        val manager = LinearLayoutManager(rootView.context)
        rc_mealHistory.layoutManager = manager
        rc_mealHistory.setHasFixedSize(true)

        val mealListAdapter=MealHistoryRecyclerViewAdapter(recordList)
        rc_mealHistory.adapter=mealListAdapter

    }
    private fun listToCSV(){
        try{
            if(historyList.size>0){
                val csv: String =
                    Environment.getExternalStorageDirectory().absolutePath
                        .toString() + "/FoodHistory.csv" // Here csv file name is MyCsvFile.csv
                var writer = CSVWriter( FileWriter(csv))
                writer.writeAll(historyList as MutableList<Array<String>>); // data is adding to csv

                writer.close();
            }

        }catch (e: Exception){
            Log.e("App", e.message.toString())
        }
    }

}