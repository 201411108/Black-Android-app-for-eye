package com.example.black

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.SeekBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.graphics.toColor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.background.*
import java.security.Permission
import java.util.jar.Manifest
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //어플리케이션 실행 시 서비스가 실행중인지 확인, 실행중이면 이미지 크기를 큰것으로 교체
        if (checkState()) {
            turnOnBtn.setImageResource(R.drawable.end_button2)
            isRunning = true
        }

        turnOnBtn.setOnClickListener {


            //Overlay 권한 확인
            if (PermissionCheck()) {
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


                    isRunning = true
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
        }

        tipBtn.setOnClickListener {
            val intent = Intent(this, EyeTipSelectActivity::class.java)
            startActivity(intent)
        }

        //TODO("seekbar 3개")
        periodSeekBar.setOnSeekBarChangeListener(PeriodSeekBarListener())
        sustainTimeSeekBar.setOnSeekBarChangeListener(SustainTimeSeekBarListener())
        colorSeekBar.setOnSeekBarChangeListener(ColorSeekBarListener())

    } // end of onCreate


    fun PermissionCheck() : Boolean {
        if (!Settings.canDrawOverlays(this)) {
            startActivity(
                Intent(
                    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                    Uri.parse("package:" + getPackageName())
                )
            )
            return false
        }
        return true
    }

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
            periodSeekBarText.text = "주기 ${seekBar?.progress.toString()}초"
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
            sustainTimeSeekBarText.text = "지속시간 ${seekBar?.progress.toString()}ms"
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
            colorSeekBarText.text = "투명도 ${seekBar?.progress.toString()}%"
        }
    }
}
