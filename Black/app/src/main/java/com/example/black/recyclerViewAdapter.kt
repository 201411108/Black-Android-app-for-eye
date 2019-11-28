/*
 * reclycerViewAdapter.kt
 * handongkim
 * DB 정보를 가져와 리사이클러 뷰를 보여주는 어답터
 * history
 * 20191123     handongkim      init
 * 20191127     handongkim      data에 따른 recyclerViewAdapter 생성
 * 20191128     handongkim      db 연동 성공
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

class FoodRecyclerAdapter(val context : Context, val data : ArrayList<EyeDataClass.EyeFoodInfo>, val itemClick : (EyeDataClass.EyeFoodInfo) -> Unit)
    : RecyclerView.Adapter<FoodRecyclerAdapter.Holder>() {

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

    inner class Holder(itemView: View, itemclick: (EyeDataClass.EyeFoodInfo) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
        val itemImage = itemView.findViewById<ImageView>(R.id.itemImage)

        fun bind(data: EyeDataClass.EyeFoodInfo, context: Context) {
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

class TeaRecyclerAdapter(val context : Context, val data : ArrayList<EyeDataClass.EyeTeaInfo>, val itemClick : (EyeDataClass.EyeTeaInfo) -> Unit)
    : RecyclerView.Adapter<TeaRecyclerAdapter.Holder>() {

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

    inner class Holder(itemView: View, itemclick: (EyeDataClass.EyeTeaInfo) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
        val itemImage = itemView.findViewById<ImageView>(R.id.itemImage)

        fun bind(data: EyeDataClass.EyeTeaInfo, context: Context) {
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

class DrugRecyclerAdapter(val context : Context, val data : ArrayList<EyeDataClass.EyeDrug>, val itemClick : (EyeDataClass.EyeDrug) -> Unit)
    : RecyclerView.Adapter<DrugRecyclerAdapter.Holder>() {

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

    inner class Holder(itemView: View, itemclick: (EyeDataClass.EyeDrug) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)

        fun bind(data: EyeDataClass.EyeDrug, context: Context) {
            itemTitle.text = data.title

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }
}

class ExcerciseRecyclerAdapter(val context : Context, val data : ArrayList<EyeDataClass.EyeExcercise>, val itemClick : (EyeDataClass.EyeExcercise) -> Unit)
    : RecyclerView.Adapter<ExcerciseRecyclerAdapter.Holder>() {

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

    inner class Holder(itemView: View, itemclick: (EyeDataClass.EyeExcercise) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)

        fun bind(data: EyeDataClass.EyeExcercise, context: Context) {
            itemTitle.text = data.title

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }
}

class InfoRecyclerAdapter(val context : Context, val data : ArrayList<EyeDataClass.EyeInfo>, val itemClick : (EyeDataClass.EyeInfo) -> Unit)
    : RecyclerView.Adapter<InfoRecyclerAdapter.Holder>() {

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

        fun bind(data: EyeDataClass.EyeInfo, context: Context) {
            itemTitle.text = data.title

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }
}
