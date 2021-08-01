package com.app.foodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.EditTextPreference
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.foodtracker.Utils.Utils
import com.app.foodtracker.database.model.User
import com.app.foodtracker.session.SessionManager
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var tv_registerUser: TextView;
    private lateinit var et_loginEmail: EditText;
    private lateinit var et_loginPassword: EditText;
    private lateinit var authViewModel: AuthViewModel
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {

        session = SessionManager(this@LoginActivity)
        session.checkLogin()

        tv_registerUser = findViewById(R.id.tv_registerUser)
        et_loginEmail = findViewById(R.id.et_loginEmail)
        et_loginPassword = findViewById(R.id.et_loginPassword)

        tv_registerUser.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    fun validation(view: View) {

        if (et_loginEmail.text.isEmpty()) {
            et_loginEmail.error = getString(R.string.required)
            return
        }
        if (Utils.emailValidation(et_loginEmail.text.toString().trim()).not()) {
            et_loginEmail.error = getString(R.string.invalidEmail)
            return
        }
        if (et_loginPassword.text.isEmpty()) {
            et_loginPassword.error = getString(R.string.required)
            return
        }
        login()
    }

    private fun login() {
        try {
            var userInfo = authViewModel.loginUser(
                this@LoginActivity,
                et_loginEmail.text.toString().trim(),
                et_loginPassword.text.toString().trim()
            )
            if (userInfo.firstName.isNullOrEmpty().not()) {

                onLoginSuccess(userInfo)
            } else {
                showToast("Login Fail")
            }
        } catch (e: Exception) {
            showToast(e.message.toString())
        }
    }

    private fun showToast(message: String) {
        Utils.showToast(this@LoginActivity, message)
    }

    private fun onLoginSuccess(userInfo: User) {
        session.createLoginSession(userInfo)
        showToast("Login Successfully")
        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        finishAffinity()
    }
}