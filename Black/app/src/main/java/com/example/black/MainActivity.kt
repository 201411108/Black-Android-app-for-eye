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
import android.widget.Toast
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
                    val intent = Intent(this, NormalService::class.java)
                    intent.putExtra("inputPeriod", periodSeekBar.progress.toString().toLong()*1000)
                    intent.putExtra("inputSustainTime", sustainTimeSeekBar.progress.toString().toLong())
                    intent.putExtra("inputColor", colorSeekBar.progress.toString().toInt()*250/100)
                    startService(intent)
                    turnOnBtn.setImageResource(R.drawable.end_button2)
                    Toast.makeText(applicationContext,"3초뒤에 어플리케이션이 종료됩니다.", Toast.LENGTH_SHORT).show()
                    Handler().postDelayed(Runnable {
                        finish()
                    },4000)
                    isRunning = true
                }

                //실행 -> 중지
                else {
                    turnOnBtn.setImageResource(R.drawable.start_button)
                    stopService(Intent(this, NormalService::class.java))
                    isRunning = false
                }
            }
        }

        tipBtn.setOnClickListener {
            val intent = Intent(this, EyeTipSelectActivity::class.java)
            startActivity(intent)
        }

        // TODO :: .ini 파일을 활용하여 사용자가 설정했던 값을 기억하고 있다가 다시 실행할 때 불러오기
        //         onCreate 초반부에 ini 파일 값을 불러와서 Seekbar 세팅하는 부분을 두기
        //         아마 ini 파일에 저장하는 것은 onDelete()와 같은 메소드를 활용하면 되지 않을까?
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
