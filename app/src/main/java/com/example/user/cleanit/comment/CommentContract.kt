package com.example.user.cleanit.comment

import com.example.user.cleanit.common.BasePresenter
import com.example.user.cleanit.common.BaseView
import com.example.user.cleanit.models.CommentDataHelper

interface CommentContract {
    interface View: BaseView<Presenter>{
        fun onGetDataSuccess(comments: ArrayList<CommentDataHelper>)
        fun onAddCommentSuccess()
        fun onAddCommentError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun getData(companyId: Int)
        fun addComment(comment: CommentDataHelper, commentNumber: Int)
    }
}