package com.example.user.cleanit.models

data class CommentDataHelper(var userId: String,
                             var companyId: Int,
                             var text: String) {
    constructor(): this("", 0, "")
}