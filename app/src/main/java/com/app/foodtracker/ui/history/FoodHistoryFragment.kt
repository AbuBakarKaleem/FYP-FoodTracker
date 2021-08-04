package com.app.foodtracker.ui.history

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.foodtracker.R
import com.app.foodtracker.adapters.MealHistoryRecyclerViewAdapter
import com.app.foodtracker.database.model.MealRecord
import com.opencsv.CSVWriter
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import android.os.Environment.DIRECTORY_DOWNLOADS as DIRECTORY_DOWNLOADS1

class FoodHistoryFragment : Fragment() {

    private lateinit var tv_noDataFound:AppCompatTextView
    private lateinit var btn_share:AppCompatButton
    private lateinit var rc_mealHistory:RecyclerView
    private lateinit var viewModel: FoodHistoryViewModel
    private lateinit var rootView:View
    private lateinit var historyList:ArrayList<MealRecord>
    private lateinit var progressDialog: ProgressDialog

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
        progressDialog  = ProgressDialog(rootView.context)
        progressDialog.setMessage("Prcessing..");
        btn_share.setOnClickListener {
            progressDialog.show()
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
    private fun populateRecyclerView(recordList: ArrayList<MealRecord>){

        val manager = LinearLayoutManager(rootView.context)
        rc_mealHistory.layoutManager = manager
        rc_mealHistory.setHasFixedSize(true)

        val mealListAdapter=MealHistoryRecyclerViewAdapter(recordList)
        rc_mealHistory.adapter=mealListAdapter

    }
    private fun listToCSV(){
        try{
            if(historyList.size>0){
                val csvFilePath: String = activity?.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath
                        .toString() + "/FoodHistory.csv" // Here csv file name is MyCsvFile.csv
                var stringBuilder=StringBuilder()
                var finalList:MutableList<Array<String>> = mutableListOf()
                for(item in historyList){
                    stringBuilder.clear()
                    stringBuilder.append(item.mealType)
                    stringBuilder.append(",")
                    stringBuilder.append(item.mealDate +" "+ item.mealTime)
                    stringBuilder.append(",")
                    stringBuilder.append(item.mealDescription)
                    stringBuilder.append("\n")
                    finalList.add(arrayOf(stringBuilder.toString()))
                }
                val writer = CSVWriter( FileWriter(csvFilePath))
                writer.writeAll(finalList); // data is adding to csv
                writer.close();
            }

        }catch (e: Exception){
            Log.e("App", e.message.toString())
        }
        finally {
            Timer().schedule(1500){
                progressDialog.dismiss()
            }
        }
    }
    /*private fun listToCSV(){
        val filename = "FoodHistory.csv"
        val directoryDownload: File =
            rootView.context.getExternalFilesDir(DIRECTORY_DOWNLOADS1)!!
        val logDir =
            File(directoryDownload, "FoodTracker")

        logDir.mkdirs()
        val file = File(logDir, filename)

        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(file, true)
            //outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            var i = 0
            while (i < historyList.size) {
                outputStream.write((historyList.get(i).mealType + ",").toByteArray())
                outputStream.write((historyList.get(i).mealDate + ",").toByteArray())
                outputStream.write((historyList.get(i).mealDescription + "\n").toByteArray())
            }
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        finally {
            progressDialog.dismiss()
        }
    }*/

}