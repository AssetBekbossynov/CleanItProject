package com.example.user.cleanit.helpers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.user.cleanit.models.CleanType
import android.view.LayoutInflater
import com.example.user.cleanit.R
import android.graphics.Color
import kotlinx.android.synthetic.main.type_row.view.*

class TypeAdapter(var cleanTypes: ArrayList<CleanType>, var context: Context, listener: TypeListener): RecyclerView.Adapter<TypeAdapter.ViewHolder>() {

    var selectedList: HashMap<Int, Double> = HashMap()
    var mCallback: TypeListener? = null
    var totalCost: Double = 0.0

    init {
        mCallback = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.type_row, parent, false)

        val vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return cleanTypes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.typeName.setText(cleanTypes[position].name)
        holder.itemView.typeCost.setText(cleanTypes[position].cost.toString() + "")
        holder.itemView.setOnClickListener { view ->
            if (selectedList.containsKey(position)) {
                selectedList.remove(position)
                view.setBackgroundColor(Color.WHITE)
            } else {
                selectedList.put(position, cleanTypes[position].cost)
                view.setBackgroundColor(Color.argb(255, 111, 186, 169))
            }
            totalCost = 0.0
            mCallback!!.onClick(getTotalCost(cleanTypes.size))
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getTotalCost(size: Int): Double {
        for (i in 0 until size) {
            if (selectedList.containsKey(i))
                totalCost += selectedList[i]!!
        }
        return totalCost
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}