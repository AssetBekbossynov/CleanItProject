package com.example.user.cleanit.company

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.cleanit.R
import kotlinx.android.synthetic.main.activity_company.*
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import com.example.user.cleanit.comment.CommentActivity
import com.example.user.cleanit.helpers.TypeAdapter
import com.example.user.cleanit.helpers.TypeListener
import com.example.user.cleanit.models.CompanyDataHelper
import android.content.DialogInterface
import android.os.Build
import android.support.v7.app.AlertDialog

class CompanyActivity : AppCompatActivity(), TypeListener {

    lateinit var companyDataHelper: CompanyDataHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)

        val intent = getIntent()
        companyDataHelper = intent.getParcelableExtra<Parcelable>("company") as CompanyDataHelper

        comment.setOnClickListener {
            val intent = Intent(this@CompanyActivity, CommentActivity::class.java)
            intent.putExtra("id", companyDataHelper.companyId)
            startActivity(intent)
        }

        name.setText(companyDataHelper.companyName)
        description.setText(companyDataHelper.description)

        typeList.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        typeList.setLayoutManager(mLayoutManager)
        val mAdapter = TypeAdapter(companyDataHelper.cleanTypes, this, this)
        typeList.setAdapter(mAdapter)

        back.setOnClickListener {
            onBackPressed()
        }

        totalCost.setOnClickListener {
            val builder: AlertDialog.Builder
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = AlertDialog.Builder(this@CompanyActivity, android.R.style.Theme_Material_Dialog_Alert)
            } else {
                builder = AlertDialog.Builder(this@CompanyActivity)
            }
            builder.setTitle("Спасибо!")
                    .setMessage("Заказ был успешно оформлен!")
                    .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, which -> onBackPressed() })
                    .setIcon(R.drawable.ok)
                    .show()
        }
    }

    override fun onClick(cost: Double?) {
        totalCost.setText("Заказать " + cost);
    }
}