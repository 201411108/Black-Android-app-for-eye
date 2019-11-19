/*
 * 1. MainActivity.kt
 * 그냥 앱 키면 처음 나오는 액티비티
 * 로그인 기능이 원래 있어야 하는데 지금은 버튼으로 대체.
 * 버튼을 누르면 KHDStudayListActivity로 넘어감.
 */

package cs.smu.ac.embeddedsoftware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        khdGoToMainBtn.setOnClickListener {
            val nextIntent = Intent(this, KHDStudyListActivity::class.java)
            startActivity(nextIntent)
        }


    }
}
