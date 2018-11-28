package com.example.user.cleanit.di

import com.example.user.cleanit.login.LoginContract
import com.example.user.cleanit.login.LoginPresenter
import com.example.user.cleanit.main.MainContract
import com.example.user.cleanit.main.MainPresenter
import com.example.user.cleanit.registration.RegistrationContract
import com.example.user.cleanit.registration.RegistrationPresenter
import org.koin.dsl.module.module

val appModule = module {

    factory { (view: MainContract.View) -> MainPresenter(view) as MainContract.Presenter }
    factory { (view: RegistrationContract.View) -> RegistrationPresenter(view) as RegistrationContract.Presenter }
    factory { (view: LoginContract.View) -> LoginPresenter(view) as LoginContract.Presenter }

}

val cleanIt = listOf(appModule)