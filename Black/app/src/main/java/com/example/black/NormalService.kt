package com.example.black

import android.annotation.SuppressLint
import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.PixelFormat
import android.os.IBinder
import android.text.Layout
import android.view.*
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import kotlin.concurrent.timer

class NormalService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun test() {
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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "서비스 실행", Toast.LENGTH_SHORT).show()

        test()

        var view =
            (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.background,
                null
            )
        var manager = getSystemService("window") as WindowManager
        var params: WindowManager.LayoutParams =
            WindowManager.LayoutParams(-1, -1, 2038, 280, 1)


        manager.addView(view, params)



        manager.removeViewImmediate(view)


        //Context.LAYOUT_INFLATER_SERVICE

//        timer(period = 3000) {
//            runOnUiThred {
//                manager.addView(view, params)
//                delayhandler.postDelayed(
//                    Runnable { manager.removeViewImmediate(view) },
//                    latencyText.text.toString().toLong()
//                )
//            }
//        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "11111", Toast.LENGTH_SHORT).show()

    }

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
}
