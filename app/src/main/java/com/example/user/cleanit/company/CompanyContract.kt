package com.example.user.cleanit.company

import com.example.user.cleanit.common.BasePresenter
import com.example.user.cleanit.common.BaseView

interface CompanyContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter<View>{

    }
}