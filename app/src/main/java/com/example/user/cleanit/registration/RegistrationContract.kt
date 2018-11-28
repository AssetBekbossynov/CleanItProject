package com.example.user.cleanit.registration

import com.example.user.cleanit.common.BasePresenter
import com.example.user.cleanit.common.BaseView

interface RegistrationContract {
    interface View: BaseView<Presenter> {
        fun onSignUpSuccess()
        fun onError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun signUp(username: String, password: String, usernameString: String)
    }
}