package com.example.user.cleanit.comment

import android.media.MediaPlayer
import com.google.firebase.auth.FirebaseAuth
import com.example.user.cleanit.R.id.commentList
import com.example.user.cleanit.models.CommentDataHelper
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_comment.*


class CommentPresenter(override var view: CommentContract.View?) : CommentContract.Presenter {
    val firebaseDatabase = FirebaseDatabase.getInstance()

    val databaseReference = firebaseDatabase.getReference()
    val mAuth = FirebaseAuth.getInstance()
    var comments: ArrayList<CommentDataHelper> = ArrayList()

    override fun addComment(comment: CommentDataHelper, commentNumber: Int) {
        databaseReference.child("comments").child(commentNumber.toString()).setValue(comment, object: DatabaseReference.CompletionListener{
            override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                if (p0 != null){
                    view?.onAddCommentError(p0.message)
                }else{
                    view?.onAddCommentSuccess()
                }
            }

        })

    }

    override fun getData(companyId: Int) {
        databaseReference.child("comments").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (i in 0 until dataSnapshot.childrenCount) {
                    if (dataSnapshot.child("" + i).getValue<CommentDataHelper>(CommentDataHelper::class.java!!)!!.companyId == companyId) {
                        comments.add(dataSnapshot.child("" + i).getValue<CommentDataHelper>(CommentDataHelper::class.java!!)!!)
                    }
                }
                view?.onGetDataSuccess(comments)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}