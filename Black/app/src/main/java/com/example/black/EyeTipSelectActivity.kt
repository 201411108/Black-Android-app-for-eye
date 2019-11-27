/*
 * EyeTipActivity.kt
 * @handongkim
 * 눈 관련 정보들을 확인할 수 있는 버튼이 있는 activity
 * history
 * 20191122     handongkim      init
 * 20191127     handongkim      xml 디자인 수정
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tip_select.*

class EyeTipSelectActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_select)

        val selectBtnListener = selectBtnListener()

        foodBtn.setOnClickListener(selectBtnListener)
        teaBtn.setOnClickListener(selectBtnListener)
        drugBtn.setOnClickListener(selectBtnListener)
        excerciseBtn.setOnClickListener(selectBtnListener)
        infoBtn.setOnClickListener(selectBtnListener)

    } // end of onCreate

    private fun sendIntent(args:String) {
        // Toast.makeText(this, args + "로 이동", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EyeTipActivity::class.java)
        // TODO :: DB 연결의 시작, 각기 다른 데이터베이스를 가져와서 내용을 보여줘야 한다.
        intent.putExtra("key", args)
        startActivity(intent)
    }

    inner class selectBtnListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.foodBtn -> sendIntent(foodBtn.text.toString())
                R.id.teaBtn -> sendIntent(teaBtn.text.toString())
                R.id.drugBtn -> sendIntent(drugBtn.text.toString())
                R.id.excerciseBtn -> sendIntent(excerciseBtn.text.toString())
                R.id.infoBtn -> sendIntent(infoBtn.text.toString())
            }
        }
    }


} // end of class