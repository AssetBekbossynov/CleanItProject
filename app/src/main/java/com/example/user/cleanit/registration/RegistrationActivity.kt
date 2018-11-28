package com.example.user.cleanit.registration

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.example.user.cleanit.R
import com.example.user.cleanit.login.LoginActivity
import com.example.user.cleanit.main.MainActivity

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RegistrationActivity : AppCompatActivity(), RegistrationContract.View {

    override val presenter: RegistrationContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        loginActivity.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
        }

        registerButton.setOnClickListener {
            val usernameString = username.text.toString()
            val loginString = login.text.toString()
            val passwordString = password.text.toString()

            if (!TextUtils.isEmpty(usernameString) && !TextUtils.isEmpty(loginString) && !TextUtils.isEmpty(passwordString)) {
                presenter.signUp(loginString, passwordString, usernameString)
            }
        }
    }

    override fun onSignUpSuccess() {
        startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
    }

    override fun onError(msg: String) {

    }
}
