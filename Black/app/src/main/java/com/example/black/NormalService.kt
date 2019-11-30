package com.example.black

import android.annotation.SuppressLint
import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.drm.DrmStore.Playback.STOP
import android.graphics.PixelFormat
import android.os.Handler
import android.os.IBinder
import android.text.Layout
import android.util.Log
import android.view.*
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.google.android.gms.internal.measurement.zzsl.init
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import kotlin.concurrent.thread
import kotlin.concurrent.timer
import android.R.string.cancel
import java.util.*
import android.R.string.cancel
import android.R.attr.start
import android.os.CountDownTimer
import android.os.Looper
import android.location.LocationManager
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.background.*


class NormalService : Service() {

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
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "서비스 실행", Toast.LENGTH_SHORT).show()

        inputPeroid = intent!!.getLongExtra("inputPeriod", 1000)
        inputSustainTime = intent!!.getLongExtra("inputSustainTime", 500)
        inputColor = intent!!.getIntExtra("inputColor", 99)

        view.setBackgroundColor(Color.argb(inputColor, 0, 0, 0))

        Toast.makeText(this, "$inputPeroid   $inputSustainTime  $inputColor", Toast.LENGTH_SHORT).show()

        //10분 기준 60000 * 10
        CDT = object : CountDownTimer((600000 * 10).toLong(), inputPeroid) {
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


//        var handler = Handler(Looper.getMainLooper())
//
//        var te = object : Runnable {
//            override fun run() {
//                removeBlackView()
//            }
//        }
//
//        var dosome = Runnable {
//            fun run(){
//                Toast.makeText(this, "ttttt", Toast.LENGTH_SHORT).show()
//                addBlackView()
//                handler.postDelayed(Runnable { a.run() }, 3000)
//            }
//        }
//        dosome.run()

        //manager.removeView(view)

//        var handler = Handler(Looper.getMainLooper()).postDelayed(Runnable() {
//            addBlackView()
//        }, 1000)


//        timer(period = periodText.text.toString().toLong()) {
//            runOnUiThread {
//                manager.addView(view, params)
//                delayhandler.postDelayed(
//                    Runnable { manager.removeViewImmediate(view) },
//                    latencyText.text.toString().toLong()
//                )
//            }
//        }

//        private void startTimer(){
//            timer = new Timer();
//            timerTask = new TimerTask() {
//                public void run() {
//                    handler.post(new Runnable() {
//                        public void run(){
//                            //your code is here
//                        }
//                    });
//                }
//            };
//            timer.schedule(timerTask, 5000, 5000);
//        }

//        var handler = Handler()

//        val timer = Timer()
//
//        val TT = object : TimerTask() {
//            override fun run() {
//                if(Looper.myLooper() == null){
//                    Looper.prepare()
//                }
//                addBlackView()
//                handler.postDelayed(Runnable {
//                        removeBlackView()
//                }, 700)
//            }
//        }
////
//        timer.schedule(TT, 0, 5000) //Timer 실행
//        timer.cancel()//타이머 종료
//

//        var aaa = Counter()
//        aaa.run()

        //stopSelf()

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


//        Thread(Runnable {
//            try {
//                Log.i("1ghl","1ghl")
//                Thread.sleep(1000) // Constants.hour = 3600000 * int
//                addBlackView()
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//            removeBlackView()
//        }).start()

        //return START_STICKY
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "서비스 종료", Toast.LENGTH_SHORT).show()
        CDT.cancel()
        removeBlackView()
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

    fun init() {
        view = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.background,
            null
        )
        manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        handler = Handler()
        params = WindowManager.LayoutParams(-1, -1, 2038, 280, 1)
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