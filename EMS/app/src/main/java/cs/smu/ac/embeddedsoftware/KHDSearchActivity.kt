/*
 * KHDSearchactivity.kt
 * 무시해도 됨. 검색 화면 구현 중 중단함
 */

package cs.smu.ac.embeddedsoftware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// 검색 액티비티 뷰, 뷰 모델을 따로 만들어줘야함.(데이터베이스에서 받아와서 표시할 부분)
class KHDSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.khd_activity_search)
    }
}
