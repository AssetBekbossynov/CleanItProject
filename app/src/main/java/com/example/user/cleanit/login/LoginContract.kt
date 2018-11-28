package com.example.user.cleanit.login

import com.example.user.cleanit.common.BasePresenter
import com.example.user.cleanit.common.BaseView

interface LoginContract {
    interface View: BaseView<Presenter>{
        fun onSignInSuccess()
        fun onError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun signIn(login: String, password: String)
    }
}