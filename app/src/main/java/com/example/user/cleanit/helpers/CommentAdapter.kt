package com.example.user.cleanit.helpers

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.user.cleanit.models.CommentDataHelper
import com.google.firebase.database.DatabaseError
import com.example.user.cleanit.models.UserDataHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import android.view.LayoutInflater
import com.example.user.cleanit.R
import kotlinx.android.synthetic.main.comment_row.view.*


class CommentAdapter(var comments: ArrayList<CommentDataHelper>, var context: Context): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val v = inflater.inflate(R.layout.comment_row, parent, false)

        val databaseReference = FirebaseDatabase.getInstance().reference

        v.comment.setText(comments[position].text)

        databaseReference.child("users").child(comments[position].userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                v.username.text = dataSnapshot.getValue(UserDataHelper::class.java)?.username
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        return v
    }

    override fun getItem(position: Int): Any {
        return comments.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return comments.size
    }
}