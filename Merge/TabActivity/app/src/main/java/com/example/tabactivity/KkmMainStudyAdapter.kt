package com.example.tabactivity

import android.graphics.drawable.Drawable
import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.kkm_study_item.view.*

class StudyItem(val image: Drawable, val title: String, val content: String) {

}

class StudyRecyclerAdapter(private val items: ArrayList<StudyItem>) :
    RecyclerView.Adapter<StudyRecyclerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: StudyRecyclerAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Click ${item.title}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): StudyRecyclerAdapter.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.kkm_study_item, parent, false)
        return StudyRecyclerAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, item: StudyItem) {
            view.kkm_study_item_image.setImageDrawable(item.image)
            view.kkm_study_item_title.text = item.title
            view.kkm_study_item_content.text = item.content

            view.setOnClickListener(listener)
        }
    }
}