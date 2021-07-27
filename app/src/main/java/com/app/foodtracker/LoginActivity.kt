package com.app.foodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.EditTextPreference
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.app.foodtracker.Utils.Utils

class LoginActivity : AppCompatActivity() {

    private lateinit var tv_registerUser:TextView;
    private lateinit var et_loginEmail: EditText;
    private lateinit var et_loginPassword:EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }
    private fun init(){
        tv_registerUser=findViewById(R.id.tv_registerUser)
        et_loginEmail=findViewById(R.id.et_loginEmail)
        et_loginPassword=findViewById(R.id.et_loginPassword)

        tv_registerUser.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
        }
    }
    fun validation(view: View) {

        if(et_loginEmail.text.isEmpty()){
            et_loginEmail.error=getString(R.string.required)
            return
        }
        if (Utils.emailValidation(et_loginEmail.text.toString().trim()).not()) {
            et_loginEmail.error = getString(R.string.invalidEmail)
            return
        }
        if(et_loginPassword.text.isEmpty()){
            et_loginPassword.error=getString(R.string.required)
            return
        }
        login()
    }
    private fun login() {
        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
        finish()
    }
}