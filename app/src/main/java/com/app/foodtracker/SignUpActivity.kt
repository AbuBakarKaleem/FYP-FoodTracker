package com.app.foodtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.app.foodtracker.Utils.Utils

class SignUpActivity : AppCompatActivity() {

    private lateinit var iv_registerUserBack: ImageView
    private lateinit var et_signUpFirstName: EditText
    private lateinit var et_signUpLastName: EditText
    private lateinit var et_signUpEmail: EditText
    private lateinit var et_signUpPassword: EditText
    private lateinit var et_signUpAddress: EditText
    private lateinit var et_signUpPhoneNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        init()
    }

    private fun init() {
        iv_registerUserBack = findViewById(R.id.iv_registerUserBack)
        iv_registerUserBack.setOnClickListener {
            onBackPressed()
        }

        et_signUpFirstName = findViewById(R.id.et_signUpFirstName)
        et_signUpLastName = findViewById(R.id.et_signUpLastName)
        et_signUpEmail = findViewById(R.id.et_signUpEmail)
        et_signUpPassword = findViewById(R.id.et_signUpPassword)
        et_signUpAddress = findViewById(R.id.et_signUpAddress)
        et_signUpPhoneNumber = findViewById(R.id.et_signUpPhoneNumber)

    }

    fun signUpValidation(view: View) {

        if (et_signUpFirstName.text.isEmpty()) {
            et_signUpFirstName.error = getString(R.string.required)
            return
        }
        if (et_signUpLastName.text.isEmpty()) {
            et_signUpLastName.error = getString(R.string.required)
            return
        }
        if (et_signUpEmail.text.isEmpty()) {
            et_signUpEmail.error = getString(R.string.required)
            return
        }
        if (Utils.emailValidation(et_signUpEmail.text.toString().trim()).not()) {
            et_signUpEmail.error = getString(R.string.invalidEmail)
            return
        }
        if (et_signUpPassword.text.isEmpty()) {
            et_signUpPassword.error = getString(R.string.required)
            return
        }
        if (et_signUpPassword.text.length <= 3) {
            et_signUpPassword.error = getString(R.string.password_length_error)
            return
        }
        if (et_signUpAddress.text.isEmpty()) {
            et_signUpAddress.error = getString(R.string.required)
            return
        }
        if (et_signUpPhoneNumber.text.isEmpty()) {
            et_signUpPhoneNumber.error = getString(R.string.required)
            return
        }

        registerUser()

    }

    private fun registerUser() {
        Utils.showToast(this@SignUpActivity, "Work in Progress")
    }
}