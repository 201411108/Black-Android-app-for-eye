package cs.smu.ac.embeddedsoftware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.jhy_fragment_setting.*
import kotlinx.android.synthetic.main.jhy_activity_main.*
import kotlinx.android.synthetic.main.jhy_search_activity.*

class JHYMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jhy_activity_main)
        //setContentView(R.layout.jhy_fragment_setting)
        /*button.setOnClickListener { view ->
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

                imageView10.setOnClickListener { view ->
                    setContentView(R.layout.jhy_fragment_setting)
                }
            }

        }*/
        button.setOnClickListener {
            var intent = Intent(this, JHYSettingActivity::class.java)
            startActivity(intent)
        }




//        textView2.setOnClickListener { view ->
//            textView3.text = "view 전환"
//        }
    }
}
