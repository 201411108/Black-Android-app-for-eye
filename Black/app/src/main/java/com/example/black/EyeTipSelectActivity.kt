/*
 * EyeTipActivity.kt
 * @handongkim
 * 눈 관련 정보들을 확인할 수 있는 버튼이 있는 activity
 * history
 * 20191122     handongkim      init
 * 20191127     handongkim      xml 디자인 수정, 버튼에 따라 다른 DB가 연동되도록 구현
 * 20191130     handongkim      운동 및 눈 상식 추후 준비 중으로 변경
 * 20191206     handongkim      해야 할 일 추가
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tip_select.*

class EyeTipSelectActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_select)

        closeButton.setOnClickListener {
            finish()
        }

        val selectBtnListener = selectBtnListener()

        // TODO :: 각 버튼을 클릭할 때 Firebase에서 DB를 가져와서 RoomDB로 저장
        foodBtn.setOnClickListener(selectBtnListener)
        teaBtn.setOnClickListener(selectBtnListener)
        drugBtn.setOnClickListener(selectBtnListener)
        exerciseBtn.setOnClickListener {
            Toast.makeText(this, "준비 중입니다!", Toast.LENGTH_SHORT).show()
        }
        infoBtn.setOnClickListener{
            Toast.makeText(this, "준비 중입니다!", Toast.LENGTH_SHORT).show()
        }

    } // end of onCreate

    private fun sendIntent(dbkey : String, title : String) {
        // Toast.makeText(this, args + "로 이동", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EyeTipActivity::class.java)

        intent.putExtra("DBKEY", dbkey)
        intent.putExtra("TITLE", title)
        startActivity(intent)
    }

    inner class selectBtnListener : View.OnClickListener {

        val FOOD = "FOOD"
        val TEA = "TEA"
        val DRUG = "DRUG"
        // val EXERCISE = "EXERCISE"
        // val INFO = "INFO"

        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.foodBtn -> sendIntent(FOOD, "눈에 좋은 음식")
                R.id.teaBtn -> sendIntent(TEA, "눈에 좋은 티(Tea)")
                R.id.drugBtn -> sendIntent(DRUG, "눈에 좋은 약")
                // R.id.exerciseBtn -> sendIntent(EXERCISE, exerciseBtn.text.toString())
                // R.id.infoBtn -> sendIntent(INFO, infoBtn.text.toString())
            }
        }
    }


} // end of class