/*
 * eachTipActivity.kt
 * @handongkim
 * recyclerview의 각 아이템 클릭 시 세부 정보를 보여주는 액티비티
 * history
 * 20191124     handongkim      init
 */

package com.example.black

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.each_tip_view.*

class eachTipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.each_tip_view)

        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        tipID.text = id
        tipTitle.text = title
        tipContent.text = content

    }
}