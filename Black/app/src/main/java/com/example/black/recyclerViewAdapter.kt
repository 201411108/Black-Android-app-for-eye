package com.example.black

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclerViewAdapter(val context : Context, val eyeFoodInfo : ArrayList<eyeFood>, val itemClick : (eyeFood) -> Unit)
    : RecyclerView.Adapter<recyclerViewAdapter.Holder>() {

    override fun getItemCount(): Int {
        return eyeFoodInfo.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(eyeFoodInfo[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.tip_item_view, parent, false)
        return Holder(view, itemClick)
    }

    inner class Holder(itemView: View, itemclick: (eyeFood) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)

        fun bind(data: eyeFood, context: Context) {
            itemTitle.text = data.title

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }
}
