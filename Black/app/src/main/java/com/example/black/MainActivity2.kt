/*
 * MainActivity2.kt
 * @handongkim
 * 어플리케이션 실행 시 실행되는 첫 화면
 * 서비스 실행, 설정, 눈 건강 관련 정보 화면 제공이 가능하다.
 * History
 * 20191122     handongkim      init
 * 20191127     handongkim      MainActivity의 서비스 실행 기능 통합
 *
 * TODO ::
 *  에러 발생 시 수정한 후 EditText 요소들의 id값 수정
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_2.*

class MainActivity2 : AppCompatActivity() {

    var isRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        turnOnBtn.setOnClickListener {

            if(isRunning) {
                // Toast.makeText(this, "서비스 실행되며 종료 버튼으로 전환 " + onOffChecker, Toast.LENGTH_SHORT).show()

                // 모든 값이 입력되어야 서비스 실행
                // 입력하지 않을 경우 토스트 메세지 발생
                isRunning = startServiceWhenNotNull(isRunning, periodText2.text.toString(), sustainTimeText2.text.toString(), colorText2.text.toString())

            } else {
                // Toast.makeText(this, "서비스 종료하며 시작 버튼으로 전환 " + onOffChecker, Toast.LENGTH_SHORT).show()
                // 구글이 서비스 실행 안되고 있는 중 -> 시작 버튼
                turnOnBtn.setImageResource(R.drawable.common_google_signin_btn_icon_light)

                // 서비스 중지
                stopService(Intent(this, NormalService::class.java))

                isRunning = true
            }
        }

        tipBtn.setOnClickListener {
            val intent = Intent(this, EyeTipSelectActivity::class.java)
            startActivity(intent)
        }

    } // end of onCreate

    private fun startServiceWhenNotNull(flag : Boolean, periodText : String, sustainTime : String, colorText : String) : Boolean {

        var isRunning= true

        if(flag) {
            if(periodText != "" && sustainTime != "" && colorText != "") {

                val intent = Intent(this, NormalService::class.java)
                intent.putExtra("inputPeriod", periodText.toLong())
                intent.putExtra("inputSustainTime", sustainTime.toLong())
                intent.putExtra("inputColor", colorText.toInt())
                startService(intent)
                // 서비스 시작 후 이미지 변경(안드로이가 서비스 실행 중 -> 중지 버튼)
                turnOnBtn.setImageResource(R.drawable.ic_launcher_foreground)
                isRunning = false

            } else {
                Toast.makeText(this, "모든 칸을 입력하세요.", Toast.LENGTH_LONG).show()
            }
        }
        return isRunning

    } // end of startServiceWhenNotNull

} // end of class