package com.example.user.cleanit.main

import com.example.user.cleanit.common.BasePresenter
import com.example.user.cleanit.common.BaseView
import com.example.user.cleanit.models.CompanyDataHelper

interface MainContract{
    interface View: BaseView<Presenter>{
        fun onLogoutSuccess()
        fun onGetDataSuccess(list: ArrayList<CompanyDataHelper>)
        fun onError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun logout()
        fun getData()
    }
}