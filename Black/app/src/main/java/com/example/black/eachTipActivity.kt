/*
 * eachTipActivity.kt
 * @handongkim
 * recyclerview의 각 아이템 클릭 시 세부 정보를 보여주는 액티비티
 * history
 * 20191124     handongkim      init
 * 20191127     handongkim      바뀐 xml 적용
 * 20191128     handongkim      db 결과 반영
 */

package com.example.black

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.tip_detail_item.*

class eachTipActivity : AppCompatActivity() {

    val IMAGEPATH = "IMAGEPATH"
    val NAME = "NAME"
    val COST = "COST"
    val ELEMENT = "ELEMENT"
    val EFFECT = "EFFECT"
    val EXPLAIN = "EXPLAIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tip_detail_item)

        val imagePath = intent.getStringExtra(IMAGEPATH)
        val name = intent.getStringExtra(NAME)
        val cost = intent.getStringExtra(COST)
        val element = intent.getStringExtra(ELEMENT)
        val effect = intent.getStringExtra(EFFECT)
        val explain = intent.getStringExtra(EXPLAIN)

        FirebaseStorage.getInstance().getReference().child(imagePath).downloadUrl.addOnSuccessListener {
            Glide.with(this).load(it).into(itemDetailImage)
        }.addOnFailureListener{
            itemDetailImage.setImageResource(R.drawable.ic_launcher_foreground)
        }

        itemDetailTitle.text = name
        itemDetailContent.text = """
            가격 : $cost
            성분 : $element
            효능 : $effect
            설명 : $explain
        """.trimIndent()

    }
}