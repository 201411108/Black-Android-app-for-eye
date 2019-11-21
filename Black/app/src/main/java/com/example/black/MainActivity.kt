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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Settings.System.SCREEN_BRIGHTNESS

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_SETTINGS), 0)

        var value = Settings.System.getInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS)

        var params = window.attributes
        var bright = params.screenBrightness

        button.setOnClickListener {
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_SETTINGS), 0)

            params.screenBrightness = 1.0f

            var value = Settings.System.getInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS)

            button.text = value.toString()

           // Settings.System.putInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS, 10)

        }

        button2.setOnClickListener {
            startActivity(Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())))
        }

        button3.setOnClickListener {
            background.setBackgroundColor(Color.argb(250,0,0,250))
        }

        service_init.setOnClickListener {
            var intent = Intent(this, NormalService::class.java)
            startForegroundService(intent)
        }

        service_stop.setOnClickListener {
            var view = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.background, null)
            var manager = getSystemService("window") as WindowManager
            var params : WindowManager.LayoutParams = WindowManager.LayoutParams(-1, -1, 2038,280,1)
            var delayhandler = Handler()

            timer(period = 1000) {
                runOnUiThread {
                    manager.addView(view, params)
                    delayhandler.postDelayed(
                        Runnable { manager.removeViewImmediate(view) },
                        latencyText.text.toString().toLong()
                    )
                }
            }
        }

        button4.setOnClickListener {
        }

    }
}
