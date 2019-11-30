/*
 * reclycerViewAdapter.kt
 * handongkim
 * DB 정보를 가져와 리사이클러 뷰를 보여주는 어답터
 * history
 * 20191123     handongkim      init
 * 20191127     handongkim      data에 따른 recyclerViewAdapter 생성
 * 20191128     handongkim      db 연동 성공
 * 20191130     handongkim      recyclerViewAdapter 모듈화
 */

package com.example.black

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

class RecyclerViewAdapter(val context : Context, val data : ArrayList<EyeDataClass.EyeInfo>, val itemClick : (EyeDataClass.EyeInfo) -> Unit)
    : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.tip_recyclerview_item, parent, false)
        return Holder(view, itemClick)
    }

    inner class Holder(itemView: View, itemclick: (EyeDataClass.EyeInfo) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
        val itemImage = itemView.findViewById<ImageView>(R.id.itemImage)

        fun bind(data: EyeDataClass.EyeInfo, context: Context) {
            itemTitle.text = data.name
            FirebaseStorage.getInstance().getReference().child(data.imagePath).downloadUrl.addOnSuccessListener {
                Glide.with(context).load(it).into(itemImage)
            }.addOnFailureListener{
                itemImage.setImageResource(R.drawable.ic_launcher_foreground)
            }

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }
}