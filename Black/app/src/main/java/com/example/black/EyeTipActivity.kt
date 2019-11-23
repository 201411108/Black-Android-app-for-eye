/*
 * EyeTipActivity.kt
 * @handongkim
 * intent를 통해 어떤 DB와 연동되는지 확인 후 해당 데이터를 반영하는 액티비티
 * history
 * 20191123     handongkim      init
 */

package com.example.black

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_tips_view.*

class EyeTipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_view)

        // 추후 매개변수로 사용하기 위해 선언
        val response = intent.getStringExtra("key")
        tipTitle.text = response

        val tipRecyclerView : RecyclerView = findViewById(R.id.tipRecyclerView)
        // TODO :: 넘겨받는 intent에 따라서 다른 결과를 가져오도록 만들어야 한다.
        val recyclerAdapter = recyclerViewAdapter(this, EyeDataClass().eyeFoodInfo) {
            Toast.makeText(this, "제목 : ${it.title}", Toast.LENGTH_SHORT).show()
        }

        tipRecyclerView.adapter = recyclerAdapter

        val lm = LinearLayoutManager(tipRecyclerView.context)
        tipRecyclerView.layoutManager = lm
        tipRecyclerView.setHasFixedSize(true)
    }

    // TODO :: DB구조에 따라 리팩토링
//    fun getRightData(param:String) : recyclerViewAdapter {
//        val param = intent.getStringExtra("key").toString()
//
//        when(param) {
//            "눈에 좋은 음식" -> {
//                return recyclerViewAdapter(this, EyeDataClass().eyeFoodInfo) {
//                    Toast.makeText(this, "제목 : ${it.title}", Toast.LENGTH_SHORT).show()
//                }
//            }
//            "눈에 좋은 차(tea)" -> {
//                return recyclerViewAdapter(this, EyeDataClass().eyeTeaInfo) {
//                    Toast.makeText(this, "제목 : ${it.title}", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
}