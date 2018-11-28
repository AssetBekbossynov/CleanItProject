package com.example.user.cleanit.helpers

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.user.cleanit.R
import com.example.user.cleanit.company.CompanyActivity
import com.example.user.cleanit.models.CompanyDataHelper

import java.util.ArrayList

import kotlinx.android.synthetic.main.company_row.view.*

class CompanyListAdapter(var context: Context, var companyDataHelpers: ArrayList<CompanyDataHelper>) : BaseAdapter() {

    override fun getCount(): Int {
        return companyDataHelpers.size
    }

    override fun getItem(position: Int): Any {
        return companyDataHelpers[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View? {
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var list = companyDataHelpers as MutableList<CompanyDataHelper>

        val inflater = LayoutInflater.from(context)

        Logger.msg("check ${companyDataHelpers[position]}")

        val v = inflater.inflate(R.layout.company_row, viewGroup, false)

        v.companyName.text = companyDataHelpers.get(position).companyName
        v.description.text = companyDataHelpers.get(position).description

        v.setOnClickListener {
            val intent = Intent(context, CompanyActivity::class.java)
            intent.putExtra("company", companyDataHelpers[position])
            context.startActivity(intent)
        }

        return v
    }
}
