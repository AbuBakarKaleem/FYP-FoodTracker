package com.app.foodtracker.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.foodtracker.HomeActivity
import com.app.foodtracker.LoginActivity
import com.app.foodtracker.R
import com.app.foodtracker.session.SessionManager

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var btn_backToHome:AppCompatButton
    private lateinit var btn_logOut:AppCompatButton
    private lateinit var rootView: View
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        rootView = inflater.inflate(R.layout.fragment_slideshow, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_slideshow)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        sessionManager= SessionManager(rootView.context)
        btn_backToHome=rootView.findViewById(R.id.btn_backToHome)
        btn_logOut=rootView.findViewById(R.id.btn_logOut)

        btn_backToHome.setOnClickListener { findNavController().navigateUp() }
        btn_logOut.setOnClickListener {

            sessionManager.logoutUser()
            /*rootView.context.startActivity(Intent( rootView.context,LoginActivity::class.java))
            activity?.finish()*/
        }
    }
}