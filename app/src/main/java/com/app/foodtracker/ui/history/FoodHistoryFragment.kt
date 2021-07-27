package com.app.foodtracker.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.foodtracker.R

class FoodHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = FoodHistoryFragment()
    }

    private lateinit var viewModel: FoodHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.food_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}