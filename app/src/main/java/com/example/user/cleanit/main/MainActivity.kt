package com.example.user.cleanit.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.user.cleanit.R
import com.example.user.cleanit.helpers.CompanyListAdapter
import com.example.user.cleanit.helpers.Logger
import com.example.user.cleanit.login.LoginActivity
import com.example.user.cleanit.models.CompanyDataHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MainContract.View {

    internal var companyDataHelpers: ArrayList<CompanyDataHelper>? = null

    override val presenter: MainContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        companyDataHelpers = ArrayList()
//        firebaseDatabase = FirebaseDatabase.getInstance()
//        databaseReference = firebaseDatabase.getReference("companies")
//        mAuth = FirebaseAuth.getInstance()
//
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                companyDataHelpers = ArrayList()
//
//                for (i in 0 until dataSnapshot.childrenCount) {
//                    companyDataHelpers.add(dataSnapshot.child("" + i).getValue<CompanyDataHelper>(CompanyDataHelper::class.java))
//                }
//                val adapter = CompanyListAdapter(this@MainActivity, companyDataHelpers)
//                companiesListView.adapter = adapter
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//
//            }
//        })
        logout.setOnClickListener {
            presenter.logout()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getData()
    }

    override fun onLogoutSuccess() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onGetDataSuccess(list: ArrayList<CompanyDataHelper>) {

        val adapter = CompanyListAdapter(this@MainActivity, list)
        companiesListView.adapter = adapter
    }

    override fun onError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
