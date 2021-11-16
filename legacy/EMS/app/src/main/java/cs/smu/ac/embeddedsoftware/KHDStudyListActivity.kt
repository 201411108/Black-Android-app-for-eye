/*
 * 2. KHDStudylistActivirt.kt
 * 기본 제공되는 바텀 네비게이션 바 액티비티를 활용해서 구현
 * 네비게이션 바의 각 구성 요소들을 fragment로 ui 패키지 내에 구현되어 있음
 * 현재 dashboard 관련 함수들이 ui 패키지 밖에 있지만 추후 리팩토링 과정에서 패키지화 할 것 생각 중
 * ui 패키지 내 dashboard 패키지만 확인할 것!
 */

package cs.smu.ac.embeddedsoftware

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.khd_activity_study_list.*

class KHDStudyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.khd_activity_study_list)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // 글쓰기 활동이 일어날 경우 네비게이션 바가 dashboard로 세팅
        setNavView()

    }

    // 게시판 글쓰기 화면에서 글쓰기 버튼 클릭 시 인텐트가 전달되는데
    // 해당 인텐트를 받아서 네비게이션 바를 dashboard로 세팅해주는 함수
    // 인텐트가 없을 경우 home 화면을 띄워줌.
    fun setNavView() {
        if(intent.getStringExtra("key") == "new item added") {
            nav_view.setSelectedItemId(R.id.navigation_dashboard)
        }
    }

}
