package cs.smu.ac.embeddedsoftware

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.jhy_activity_myinfo.*

class JHYMyInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jhy_activity_myinfo)
        imageView10.setOnClickListener {
            var intent = Intent(this, JHYSettingActivity::class.java)
            startActivity(intent)
        }
    }
}