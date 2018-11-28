package com.example.user.cleanit.models

data class UserDataHelper(var username: String?,
                          var userId: String?) {
    constructor() : this("", null)
}