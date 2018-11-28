package com.example.user.cleanit.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.user.cleanit.R
import com.example.user.cleanit.main.MainActivity
import com.example.user.cleanit.registration.RegistrationActivity

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

import org.w3c.dom.Text

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override val presenter: LoginContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerActivity.setOnClickListener { startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java)) }
        loginButton.setOnClickListener {
            val loginString = login.text.toString()
            val passwordString = password.text.toString()

            if (!TextUtils.isEmpty(loginString) && !TextUtils.isEmpty(passwordString)) {
                presenter.signIn(loginString, passwordString)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onSignInSuccess() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun onError(msg: String) {
    }
}
