package cs.smu.ac.embeddedsoftware

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// MUST CHECK :: dashboardRecyclerAdapter와 완전 유사한데 굳이 둘 필요가 있을까? 일단 보류, 안쓰면 삭제 예정
class KHDDashboardSearchRecyclerAdapter(val context : Context, val dataArray : ArrayList<dashBoardClass>) : RecyclerView.Adapter<KHDDashboardSearchRecyclerAdapter.Holder>() {

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataArray[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.dashboard_item_view, parent, false)
        return Holder(view)
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.titleTv)
        val itemText = itemView.findViewById<TextView>(R.id.itemTv)

        fun bind(data : dashBoardClass, context : Context) {
            itemTitle.text = data.itemName
            itemText.text = data.itemBody
        }
    }
}