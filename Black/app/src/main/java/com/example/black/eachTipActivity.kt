/*
 * eachTipActivity.kt
 * @handongkim
 * recyclerview의 각 아이템 클릭 시 세부 정보를 보여주는 액티비티
 * history
 * 20191124     handongkim      init
 * 20191127     handongkim      바뀐 xml 적용
 */

package com.example.black

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tip_detail_item.*

class eachTipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tip_detail_item)

        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        itemDetailTitle.text = title
        itemDetailContent.text = content

    }
}