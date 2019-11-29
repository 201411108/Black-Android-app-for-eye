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

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_2.*

class MainActivity2 : AppCompatActivity() {

    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        //어플리케이션 실행 시 서비스가 실행중인지 확인, 실행중이면 이미지 크기를 큰것으로 교체
        if (checkState()) {
            turnOnBtn.setImageResource(R.drawable.end_button2)
            isRunning = true
        }

        turnOnBtn.setOnClickListener {

            //Overlay 권한 확인
            if (!Settings.canDrawOverlays(this)) {
                startActivity(
                    Intent(
                        "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                        Uri.parse("package:" + getPackageName())
                    )
                )
            }
//            TODO("이미지 selector할지, 한다면 뭐로 할지")

            //중지 -> 실행
            if(!isRunning) {
                // Toast.makeText(this, "서비스 실행되며 종료 버튼으로 전환 " + onOffChecker, Toast.LENGTH_SHORT).show()

                // 모든 값이 입력되어야 서비스 실행
                // 입력하지 않을 경우 토스트 메세지 발생
//                isRunning = !startServiceWhenNotNull(!isRunning, periodText2.text.toString(), sustainTimeText2.text.toString(), colorText2.text.toString())
                //isRunning = !startServiceWhenNotNull(!isRunning, periodSeekBar.progress.toString(), sustainSeekBar.progress.toString(), colorSeekBar.progress.toString())

                val intent = Intent(this, NormalService::class.java)
                intent.putExtra("inputPeriod", periodSeekBar.progress.toString().toLong()*1000)
                intent.putExtra("inputSustainTime", sustainTimeSeekBar.progress.toString().toLong())
                intent.putExtra("inputColor", colorSeekBar.progress.toString().toInt()*250/100)
                startService(intent)
                // 서비스 시작 후 이미지 변경(안드로이가 서비스 실행 중 -> 중지 버튼)
                turnOnBtn.setImageResource(R.drawable.end_button2)


                isRunning = true;
            }

            //실행 -> 중지
            else {
                // Toast.makeText(this, "서비스 종료하며 시작 버튼으로 전환 " + onOffChecker, Toast.LENGTH_SHORT).show()
                // 구글이 서비스 실행 안되고 있는 중 -> 시작 버튼
                turnOnBtn.setImageResource(R.drawable.start_button)

                // 서비스 중지
                stopService(Intent(this, NormalService::class.java))
                isRunning = false
            }
        }

        tipBtn.setOnClickListener {
            val intent = Intent(this, EyeTipSelectActivity::class.java)
            startActivity(intent)
        }

        isgBtn.setOnClickListener {
            val intent = Intent(this, ISGTestActivity::class.java)
            startActivity(intent)
        }

        //TODO("seekbar 3개")
        periodSeekBar.setOnSeekBarChangeListener(PeriodSeekBarListener())
        sustainTimeSeekBar.setOnSeekBarChangeListener(SustainTimeSeekBarListener())
        colorSeekBar.setOnSeekBarChangeListener(ColorSeekBarListener())

    } // end of onCreate

    // TODO :: SeekBar로 변경하며 필요 없어짐

    //서비스가 이미 실행중인지 확인하는 함수
    fun checkState(): Boolean {
        for(service in (getSystemService(Context.ACTIVITY_SERVICE)as ActivityManager).getRunningServices(Integer.MAX_VALUE)){
            if (NormalService::class.java.name == service.service.className) {
                return true
            }
        }
        return false
    }

    inner class PeriodSeekBarListener : SeekBar.OnSeekBarChangeListener{

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            periodSeekBar.progress = progress
            periodSeekBarText.text = "${seekBar?.progress.toString()}초"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    inner class SustainTimeSeekBarListener : SeekBar.OnSeekBarChangeListener{

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            seekBar?.progress = (progress/10)*10
            sustainTimeSeekBarText.text = "${seekBar?.progress.toString()}ms"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    inner class ColorSeekBarListener : SeekBar.OnSeekBarChangeListener{

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            seekBar?.progress = (progress/10)*10
            colorSeekBarText.text = "${seekBar?.progress.toString()}%"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

} // end of class