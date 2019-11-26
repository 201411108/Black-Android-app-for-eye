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
            kkmTestImage.setBackgroundResource(R.drawable.ic_launcher_foreground)
            isRunning = true
        }

        //권한 확인, 권한을 이미 얻었다면 권한 추가 창을 생성하지 않음.
        overlay_permission.setOnClickListener {
            if (!Settings.canDrawOverlays(this)) {
                startActivity(
                    Intent(
                        "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                        Uri.parse("package:" + getPackageName())
                    )
                )
            }
        }

        //서비스 시작
        service_init.setOnClickListener {
            var intent = Intent(this, NormalService::class.java)
            intent.putExtra("inputPeriod", periodText.text.toString().toLong())
            intent.putExtra("inputSustainTime", sustainTimeText.text.toString().toLong())
            intent.putExtra("inputColor", colorText.text.toString().toInt())


            if (!isRunning) {
                kkmTestImage.setBackgroundResource(R.drawable.ic_launcher_foreground)
                startService(intent)
                isRunning = true
            }
        }

        //서비스 중지
        service_stop.setOnClickListener {
            if (isRunning) {
                stopService(Intent(this, NormalService::class.java))
                isRunning = false
            }
        }

        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        //임시로 만든 TEST ACTIVITY
        isgbutton.setOnClickListener {
            val intent = Intent(this, ISGTestActivity::class.java)
            startActivity(intent)
        }
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
}
