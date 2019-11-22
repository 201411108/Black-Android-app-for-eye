package com.example.black

import android.annotation.SuppressLint
import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PixelFormat
import android.os.Handler
import android.os.IBinder
import android.text.Layout
import android.view.*
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import kotlin.concurrent.thread
import kotlin.concurrent.timer

class NormalService : Service() {

    lateinit var view: View
    lateinit var manager : WindowManager

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class counter : Runnable {

        var handler = Handler()
        var count = 0

        override fun run() {
            for (count in 0..2) {

//                handler.post(Runnable { manager },1000)

                Thread.sleep(100)
            }
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "서비스 실행", Toast.LENGTH_SHORT).show()

        init()

        MakeNotification()

        addBlackView()

        //일정 시간 뒤 자기자신 종료
//        var handler = Handler()
//        handler.postDelayed(Runnable {this@NormalService.stopSelf()}, 2000)

//        timer(period = 2000) {

            //            runOnUiThread {
//                manager.addView(view, params)
//                delayhandler.postDelayed(
//                    Runnable { manager.removeViewImmediate(view) },
//                    latencyText.text.toString().toLong()
//                )
//            }
//        }

//        var aaa = counter()
//        aaa.run()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "서비스 종료", Toast.LENGTH_SHORT).show()

        manager.removeView(view)
    }

    fun addBlackView(): View{
//        var view = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.background, null)
//        var manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var params: WindowManager.LayoutParams = WindowManager.LayoutParams(-1, -1, 2038, 280, 1)

        manager.addView(view, params)

        return view
    }

//    더더더 어플 참조
//    public View addTopBackgroundDarkerView() {
//        int type;
//        int flag;
//        String viewId = Constants.BACKGROUND;
//        if (VERSION.SDK_INT >= 26) {
//            type = 2038;
//            flag = 280;
//        } else {
//            type = CastStatusCodes.MESSAGE_TOO_LARGE;
//            flag = 264;
//        }
//        View view = ((LayoutInflater) getSystemService("layout_inflater")).inflate(C2685R.layout.background, null);
//        this.mWindowManager.addView(view, new LayoutParams(-1, -1, type, flag, 1));
//        addViewToManage(viewId, view);
//        view.setFocusable(false);
//        view.setClickable(false);
//        view.setFocusableInTouchMode(false);
//        return view;
//    }


    fun MakeNotification() {
        var notificationIntent = Intent(this, MainActivity::class.java)
        var pedingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)


        var channelId = "test_channel"
        var channel = NotificationChannel(
            channelId,
            "service_channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.createNotificationChannel(channel)

        var builder = NotificationCompat.Builder(this, channelId)


        builder.setContentTitle("title11")
        builder.setContentText("text111")
        builder.setSmallIcon(android.R.drawable.alert_dark_frame)
        builder.setContentIntent(pedingIntent)



        manager.notify(10, builder.build())
    }

    fun init(){
        view = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.background, null)
        manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

//    override fun onCreate() {
//        super.onCreate()
//
//        var channel_id = "channel_id"
//        var channel = NotificationChannel(
//            channel_id,
//            "service_channel",
//            NotificationManager.IMPORTANCE_DEFAULT
//        )
//        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        manager.createNotificationChannel(channel)
//
//
//        var pedingIntent = PendingIntent.getActivity(this,0,Intent(this, MainActivity::class.java), 0)
//        val builder = NotificationCompat.Builder(this, channel_id)
//
//        builder.setContentTitle("Title11").setContentText("Text11").setSmallIcon(android.R.drawable.alert_dark_frame)
//        builder.setContentIntent(pedingIntent)
//
//        manager.notify(10, builder.build())
//      //  startForeground(1,builder.build())
//    }
}
