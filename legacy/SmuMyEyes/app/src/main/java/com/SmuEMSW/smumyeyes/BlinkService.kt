package com.SmuEMSW.smumyeyes

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.NotificationCompat

class BlinkService : Service() {

    lateinit var view: View
    lateinit var manager: WindowManager
    lateinit var handler: Handler
    lateinit var params: WindowManager.LayoutParams
    lateinit var CDT: CountDownTimer
    var inputPeroid: Long = 0
    var inputSustainTime: Long = 0
    var inputColor: Int = 0

    var isOnView = false

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
        init()
        MakeNotification(1)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        inputPeroid = intent!!.getLongExtra("inputPeriod", 1000)
        inputSustainTime = intent!!.getLongExtra("inputSustainTime", 500)
        inputColor = intent!!.getIntExtra("inputColor", 99)

        view.setBackgroundColor(Color.argb(inputColor, 0, 0, 0))

        //10분 기준 60000 * 10
        CDT = object : CountDownTimer((60000 * 60).toLong(), inputPeroid) {
            override fun onTick(millisUntilFinished: Long) {

                if (Looper.myLooper() == null) {
                    Looper.prepare()
                }
                addBlackView()
                handler.postDelayed(Runnable {
                    removeBlackView()
                }, inputSustainTime)
            }

            override fun onFinish() {
                //마지막에 실행할 구문
            }
        }
        CDT.start() //CountDownTimer 실행

        //return START_STICKY
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "서비스 종료", Toast.LENGTH_SHORT).show()
        CDT.cancel()
        removeBlackView()
        MakeNotification(2)
    }

    fun addBlackView(): View {
        if (!isOnView) {
            manager.addView(view, params)
            isOnView = true
        }
        return view
    }

    fun removeBlackView() {
        if (isOnView) {
            manager.removeView(view)
            isOnView = false
        }
    }

    fun MakeNotification(input: Int) {

        var notificationIntent = Intent(this, MainActivity::class.java)
        var pedingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channelId = "MyEyes_channel"
            var channel = NotificationChannel(
                channelId,
                "service_channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            var builder = NotificationCompat.Builder(this, channelId)
            builder.setContentTitle("내눈 서비스가 실행중입니다.")
            builder.setContentText("자세한 내용은 알림창을 눌러주세요.")
            builder.setSmallIcon(R.drawable.app_image)
            builder.setContentIntent(pedingIntent)
            if (input == 1) {
                manager.notify(10, builder.build())
            } else {
                manager.cancel(10)
            }
        } else {
            var builder = NotificationCompat.Builder(this)
            builder.setContentTitle("내눈 서비스가 실행중입니다.")
            builder.setContentText("자세한 내용은 알림창을 눌러주세요.")
            builder.setSmallIcon(R.drawable.app_image)
            builder.setContentIntent(pedingIntent)
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, builder.build())
        }
    }

    fun init() {
        view = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.background,
            null
        )
        manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        handler = Handler()
        params = WindowManager.LayoutParams(-1, -1, 2038, 280, 1)
    }
}
