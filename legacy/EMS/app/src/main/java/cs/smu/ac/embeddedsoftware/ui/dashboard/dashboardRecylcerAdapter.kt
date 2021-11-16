/*
 * 4. dashboardRecyclerAdapter.kt
 * RecylcerView에 대한 Adapter
 * dashboard와 관련된 recyclerview 들은 이 어답터를 불러서 사용
 */

package cs.smu.ac.embeddedsoftware.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.smu.ac.embeddedsoftware.R
import cs.smu.ac.embeddedsoftware.dashBoardClass

class dashboardRecyclerAdapter(val context : Context, val dataArray : ArrayList<dashBoardClass>, val itemClick : (dashBoardClass) -> Unit) : RecyclerView.Adapter<dashboardRecyclerAdapter.Holder>() {

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataArray[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.dashboard_item_view, parent, false)
        return Holder(view, itemClick)
    }

    inner class Holder(itemView : View, itemclick : (dashBoardClass) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.titleTv)
        val itemText = itemView.findViewById<TextView>(R.id.itemTv)

        fun bind(data : dashBoardClass, context : Context) {
            itemTitle.text = data.itemName
            itemText.text = data.itemBody

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }
}