package com.example.user.cleanit.main

import com.example.user.cleanit.models.CompanyDataHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.GenericTypeIndicator

class MainPresenter(override var view: MainContract.View?) : MainContract.Presenter {
    private var mAuth = FirebaseAuth.getInstance()
    private var databaseReference = FirebaseDatabase.getInstance().getReference("companies")

    override fun logout() {
        mAuth.signOut()
        view?.onLogoutSuccess()
    }

    override fun getData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val t = object : GenericTypeIndicator<ArrayList<CompanyDataHelper>>() {}
                val value = dataSnapshot.getValue(t)
                view?.onGetDataSuccess(value!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view?.onError(databaseError.message)
            }
        })
    }
}