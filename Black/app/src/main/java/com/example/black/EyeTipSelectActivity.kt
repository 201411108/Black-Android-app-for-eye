/*
 * EyeTipActivity.kt
 * @handongkim
 * 눈 관련 정보들을 확인할 수 있는 버튼이 있는 activity
 * history
 * 20191122     handongkim      init
 * 20191127     handongkim      xml 디자인 수정, 버튼에 따라 다른 DB가 연동되도록 구현
 * 20191130     handongkim      운동 및 눈 상식 추후 준비 중으로 변경
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

        val selectBtnListener = selectBtnListener()

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
                R.id.foodBtn -> sendIntent(FOOD, foodBtn.text.toString())
                R.id.teaBtn -> sendIntent(TEA, teaBtn.text.toString())
                R.id.drugBtn -> sendIntent(DRUG, drugBtn.text.toString())
                // R.id.exerciseBtn -> sendIntent(EXERCISE, exerciseBtn.text.toString())
                // R.id.infoBtn -> sendIntent(INFO, infoBtn.text.toString())
            }
        }
    }


} // end of class