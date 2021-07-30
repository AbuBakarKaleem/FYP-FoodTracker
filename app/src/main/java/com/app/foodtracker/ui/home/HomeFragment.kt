package com.app.foodtracker.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.foodtracker.R
import com.app.foodtracker.Utils.Utils
import com.app.foodtracker.session.SessionManager
import com.app.foodtracker.ui.addFood.AddFoodRecordFragment

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rootView: View
    private lateinit var cv_breakFast:CardView
    private lateinit var cv_snacks:CardView
    private lateinit var cv_lunch:CardView
    private lateinit var cv_dinner:CardView
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        //val textView: TextView = rootView.findViewById(R.id.text_home)
       /* homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        sessionManager=SessionManager(rootView.context)
    }
    private fun init(){

        cv_breakFast=rootView.findViewById(R.id.cv_breakFast)
        cv_dinner=rootView.findViewById(R.id.cv_dinner)
        cv_lunch=rootView.findViewById(R.id.cv_lunch)
        cv_snacks=rootView.findViewById(R.id.cv_snacks)

        cv_breakFast.setOnClickListener {
            moveToAddFoodRecord(Utils.BREAKFAST)
        }
        cv_lunch.setOnClickListener {
            moveToAddFoodRecord(Utils.LUNCH)
        }
        cv_dinner.setOnClickListener {
            moveToAddFoodRecord(Utils.DINNER)
        }
        cv_snacks.setOnClickListener {
            moveToAddFoodRecord(Utils.SNACKS)
        }

    }

    private fun moveToAddFoodRecord(value:Int){
        findNavController().navigate(HomeFragmentDirections.toAddfood(value))
    }


}