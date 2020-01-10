package com.SmuEMSW.smumyeyes

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.black.util.IniUtil
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //광고
        lateinit var mAdView : AdView

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val iniUtil = IniUtil(this)
        iniUtil.setup()

        periodSeekBar.progress = iniUtil.getIni("loadInfo", "period")!!.toInt()
        sustainTimeSeekBar.progress = iniUtil.getIni("loadInfo", "sustain")!!.toInt()
        colorSeekBar.progress = iniUtil.getIni("loadInfo", "color")!!.toInt()

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
                    val intent = Intent(this, BlinkService::class.java)
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
                    stopService(Intent(this, BlinkService::class.java))
                    isRunning = false
                }
            }
        }

        periodSeekBar.setOnSeekBarChangeListener(PeriodSeekBarListener(iniUtil))
        sustainTimeSeekBar.setOnSeekBarChangeListener(SustainTimeSeekBarListener(iniUtil))
        colorSeekBar.setOnSeekBarChangeListener(ColorSeekBarListener(iniUtil))

    } // end of onCreate


    fun PermissionCheck() : Boolean {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(this))
            return true

        else startActivity(
                Intent(
                    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                    Uri.parse("package:" + getPackageName())
                )
            )
            return false
    }

    //서비스가 이미 실행중인지 확인하는 함수
    fun checkState(): Boolean {
        for(service in (getSystemService(Context.ACTIVITY_SERVICE)as ActivityManager).getRunningServices(Integer.MAX_VALUE)){
            if (BlinkService::class.java.name == service.service.className) {
                return true
            }
        }
        return false
    }

    inner class PeriodSeekBarListener(iniUtil: IniUtil) : SeekBar.OnSeekBarChangeListener{

        val iniUtil = iniUtil

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            periodSeekBar.progress = progress
            periodSeekBarText.text = "${seekBar?.progress.toString()}초"
            iniUtil.setIni("loadInfo", "period", progress.toString())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            periodSeekBarText.text = "주기 ${seekBar?.progress.toString()}초"
        }
    }

    inner class SustainTimeSeekBarListener(iniUtil: IniUtil) : SeekBar.OnSeekBarChangeListener{

        val iniUtil = iniUtil

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            seekBar?.progress = (progress/10)*10
            sustainTimeSeekBarText.text = "${seekBar?.progress.toString()}ms"
            iniUtil.setIni("loadInfo", "sustain", seekBar?.progress.toString())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            sustainTimeSeekBarText.text = "지속시간 ${seekBar?.progress.toString()}ms"
        }
    }

    inner class ColorSeekBarListener(iniUtil: IniUtil) : SeekBar.OnSeekBarChangeListener{

        val iniUtil = iniUtil

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            seekBar?.progress = (progress/10)*10
            colorSeekBarText.text = "${seekBar?.progress.toString()}%"
            iniUtil.setIni("loadInfo", "color", seekBar?.progress.toString())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            colorSeekBarText.text = "투명도 ${seekBar?.progress.toString()}%"
        }



    }
}
