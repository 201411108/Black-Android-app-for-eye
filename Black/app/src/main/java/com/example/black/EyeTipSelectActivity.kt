/*
 * EyeTipActivity.kt
 * @handongkim
 * 눈 관련 정보들을 확인할 수 있는 버튼이 있는 activity
 * history
 * 20191122     handongkim      init
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tips.*

class EyeTipSelectActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        val selectBtnListener = btnListener()

        button2.setOnClickListener(selectBtnListener)
        button3.setOnClickListener(selectBtnListener)
        button4.setOnClickListener(selectBtnListener)
        button5.setOnClickListener(selectBtnListener)
        button6.setOnClickListener(selectBtnListener)

    } // end of onCreate

    private fun sendIntent(args:String) {
        Toast.makeText(this, args + "로 이동", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EyeTipActivity::class.java)
        intent.putExtra("key", args)
        startActivity(intent)
    }

    inner class btnListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button2 -> sendIntent(button2.text.toString())
                R.id.button3 -> sendIntent(button3.text.toString())
                R.id.button4 -> sendIntent(button4.text.toString())
                R.id.button5 -> sendIntent(button5.text.toString())
                R.id.button6 -> sendIntent(button6.text.toString())
            }
        }
    }


} // end of class