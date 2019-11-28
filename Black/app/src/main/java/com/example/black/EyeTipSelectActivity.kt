/*
 * EyeTipActivity.kt
 * @handongkim
 * 눈 관련 정보들을 확인할 수 있는 버튼이 있는 activity
 * history
 * 20191122     handongkim      init
 * 20191127     handongkim      xml 디자인 수정, 버튼에 따라 다른 DB가 연동되도록 구현
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_tip_select.*
import kotlin.random.Random

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

    private fun sendIntent(dbkey : String, title : String) {
        // Toast.makeText(this, args + "로 이동", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EyeTipActivity::class.java)
        // TODO :: DB 연결의 시작, 각기 다른 데이터베이스를 가져와서 내용을 보여줘야 한다.
        intent.putExtra("DBKEY", dbkey)
        intent.putExtra("TITLE", title)
        startActivity(intent)
    }

    inner class selectBtnListener : View.OnClickListener {

        val FOOD = "FOOD"
        val TEA = "TEA"
        val DRUG = "DRUG"
        val EXCERCISE = "EXCERCISE"
        val INFO = "INFO"

        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.foodBtn -> sendIntent(FOOD, foodBtn.text.toString())
                R.id.teaBtn -> sendIntent(TEA, teaBtn.text.toString())
                R.id.drugBtn -> sendIntent(DRUG, drugBtn.text.toString())
                R.id.excerciseBtn -> sendIntent(EXCERCISE, excerciseBtn.text.toString())
                R.id.infoBtn -> sendIntent(INFO, infoBtn.text.toString())
            }
        }
    }


} // end of class