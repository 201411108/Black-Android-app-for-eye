package com.example.jhy_embeded

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.jhy_fragment_setting.*

class JHYSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jhy_fragment_setting)
        setting_leave.setOnClickListener { view ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("")
            builder.setIcon(R.mipmap.ic_launcher_round)
            var view1 = layoutInflater.inflate(R.layout.jhy_delete_user, null)
            builder.setView(view1)

            builder.setPositiveButton("승인", null)
            builder.setNegativeButton("거부", null)
            builder.show()
        }
        setting_myinfo.setOnClickListener { view ->
            var intent = Intent(this, JHYMyInfoActivity::class.java)
            startActivity(intent)
        }
        setting_mystatus.setOnClickListener {
            var intent = Intent(this, JHYMyInfoActivity::class.java)
            startActivity(intent)
        }
        setting_notice.setOnClickListener {
            var intent = Intent(this, JHYNoticeActivity::class.java)
            startActivity(intent)
        }
        setting_report.setOnClickListener {
            var intent = Intent(this, JHYReportActivity::class.java)
            startActivity(intent)
        }
    }
}