package com.example.user.cleanit.comment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.cleanit.R
import com.example.user.cleanit.models.CommentDataHelper
import kotlinx.android.synthetic.main.activity_comment.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import com.example.user.cleanit.helpers.CommentAdapter
import android.widget.Toast
import com.example.user.cleanit.helpers.Logger
import com.google.firebase.auth.FirebaseAuth


class CommentActivity : AppCompatActivity(), CommentContract.View {

    override val presenter: CommentContract.Presenter by inject { parametersOf(this) }

    var companyId: Int? = null

    val mAuth = FirebaseAuth.getInstance()

    var commentNumber = 0
    lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        companyId = intent.getIntExtra("id", 0)

        back.setOnClickListener {
            onBackPressed()
        }

        addComment.setOnClickListener {
            if (companyId != -1) {
                if (!commentEdit.getText().toString().equals("")) {
                    val commentDataHeper = CommentDataHelper(mAuth.getCurrentUser()?.getUid()!!, companyId!!, commentEdit.getText().toString())
                    adapter.comments.clear()
                    presenter.addComment(commentDataHeper, commentNumber)
                } else {
                    Toast.makeText(this@CommentActivity, "Enter some text into the edit text", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@CommentActivity, "Error occured try later", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }

    override fun onAddCommentSuccess() {
        adapter.notifyDataSetChanged()
        commentEdit.setText("")
    }

    override fun onAddCommentError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onGetDataSuccess(comments: ArrayList<CommentDataHelper>) {
        commentNumber = comments.size
        adapter = CommentAdapter(comments, this@CommentActivity)
        commentList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.getData(companyId!!)
    }
}