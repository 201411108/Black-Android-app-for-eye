/*
 * MainActivity2.kt
 * @handongkim
 * 어플리케이션 실행 시 실행되는 첫 화면
 * 서비스 실행, 설정, 눈 건강 관련 정보 화면 제공이 가능하다.
 * History
 * 20191122     handongkim      init
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_2.*

class MainActivity2 : AppCompatActivity() {

    var onOffChecker : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        turnOnBtn.setOnClickListener {
            if(onOffChecker) {
                Toast.makeText(this, "서비스 실행되며 종료 버튼으로 전환" + onOffChecker, Toast.LENGTH_SHORT).show()
                turnOnBtn.setImageResource(R.drawable.ic_launcher_foreground)
                onOffChecker = false
            } else {
                Toast.makeText(this, "서비스 종료하며 시작 버튼으로 전환" + onOffChecker, Toast.LENGTH_SHORT).show()
                turnOnBtn.setImageResource(R.drawable.common_google_signin_btn_icon_light)
                onOffChecker = true
            }
        }

        settingBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // TODO :: 설정 결과를 Intent에 담아서 실행 서비스에 반영, 현재는 일단 화면 전환만 구현.
            //startActivityForResult(intent)
        }

        tipBtn.setOnClickListener {
            val intent = Intent(this, EyeTipSelectActivity::class.java)
            startActivity(intent)
        }

    } // end of onCreate
} // end of class