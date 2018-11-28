package com.example.user.cleanit.common

interface BaseView<out P : BasePresenter<*>> {
    val presenter: P
}