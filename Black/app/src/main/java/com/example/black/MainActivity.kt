package com.example.black

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
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Permission
import java.util.jar.Manifest
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        overlay_permission.setOnClickListener {
            startActivity(Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())))
        }

        service_init.setOnClickListener {
            if(!isRunning) {
                startService(Intent(this, NormalService::class.java))
                isRunning = true
            }
        }

        // ISSUE :: 어플리케이션 onPause 전환 후 notification을 통해 접근 시 서비스가 종료되지 않음.
        //          앱 강제 종료 이후에도 notification이 상단 메뉴에 계속 살아있음.
        service_stop.setOnClickListener {
            if (isRunning) {
                stopService(Intent(this, NormalService::class.java))
                isRunning = false
            }
        }

        // ISSUE :: 서비스 실행 중 이 버튼 클릭 시 앱이 터지는 문제 발생
        activity_test.setOnClickListener {
            var view = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.background, null)
            var manager = getSystemService("window") as WindowManager
            var params : WindowManager.LayoutParams = WindowManager.LayoutParams(-1, -1, 2038,280,1)


            var delayhandler = Handler()

            timer(period = periodText.text.toString().toLong()) {
                runOnUiThread {
                    manager.addView(view, params)
                    delayhandler.postDelayed(
                        Runnable { manager.removeViewImmediate(view) },
                        latencyText.text.toString().toLong()
                    )
                }
            }
        }

        button.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        /*
        argb 바꾸기
        layout_item이름.setBackgroundColor(Color.argb(250,0,0,250))


        */

    }
}
