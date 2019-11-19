/*
 * 8. KHDDashboardWriteView.kt
 * 게시판 글쓰기 뷰
 */

package cs.smu.ac.embeddedsoftware

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KHDDashboardWriteView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.khd_dashboard_write)

        // 버튼 클릭 시 내용을 저장하는 부분은 아직 미구현 상태
        // 인텐트를 넘겨 화면을 전환한다.
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        submitBtn.setOnClickListener {
            // 버튼 클릭 시 DB에 쿼리되어야 한다.
            // 완료 후 게시글 초기 화면으로 이동해야 하고, 이동 시 추가할 글이 가장 상단에 위치해야 한다.
            val nextIntent = Intent(this, KHDStudyListActivity::class.java)
            nextIntent.putExtra("key", "new item added")
            startActivity(nextIntent)
        }
    }

}