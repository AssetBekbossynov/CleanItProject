package com.example.user.cleanit.login

import android.content.Intent
import android.util.Log
import com.example.user.cleanit.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(override var view: LoginContract.View?) : LoginContract.Presenter {

    val mAuth = FirebaseAuth.getInstance()

    override fun signIn(login: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view?.onSignInSuccess()
                } else {
                    view?.onError(task.exception?.message!!)
                }
            }
    }
}