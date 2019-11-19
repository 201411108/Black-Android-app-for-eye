package com.example.tabactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.kkm_condition_item.view.*

class ConditionItem(val condition: String)

class ConditionRecyclerAdapter(private val items: ArrayList<ConditionItem>) :
    RecyclerView.Adapter<ConditionRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConditionRecyclerAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.kkm_condition_item, parent, false)
        return ConditionRecyclerAdapter.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ConditionRecyclerAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Click Condition ${item.condition}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(listener: View.OnClickListener, item: ConditionItem){
            view.kkm_condition_item_title.text = item.condition

            view.setOnClickListener(listener)
        }
    }
}