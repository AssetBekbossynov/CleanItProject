package com.example.user.cleanit.registration

import android.content.Intent
import android.util.Log
import com.example.user.cleanit.main.MainActivity
import com.example.user.cleanit.models.UserDataHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationPresenter(override var view: RegistrationContract.View?) : RegistrationContract.Presenter {

    val mAuth = FirebaseAuth.getInstance()

    override fun signUp(loginString: String, password: String, usernameString: String) {
        mAuth!!.createUserWithEmailAndPassword(loginString, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val database = FirebaseDatabase.getInstance()
                    val myRef = database.getReference("users")

                    val userDataHelper = UserDataHelper()
                    userDataHelper.username = usernameString
                    myRef.child(mAuth.currentUser!!.uid).setValue(userDataHelper)

                    view?.onSignUpSuccess()
                } else {
                    view?.onError(task.exception?.message!!)
                }
            }
    }
}