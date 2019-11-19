package cs.smu.ac.embeddedsoftware.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cs.smu.ac.embeddedsoftware.KHDSearchActivity
import cs.smu.ac.embeddedsoftware.MainActivity
import cs.smu.ac.embeddedsoftware.R
import kotlinx.android.synthetic.main.fragment_home.*

// 홈 네비게이션 탭의 액티비티! 뷰라고 생각하면 안된다! 표시할 데이터는 HomeViewModel에서 받아온다!
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        val addStudyBtn = root.findViewById<FloatingActionButton>(R.id.khdAddStudyBtn)

        addStudyBtn.setOnClickListener {
            // TODO :: 스터디 추가 페이지로 이동. 현재는 메인 페이지로 이동 중
            val tmpIntent = Intent(it.context, MainActivity::class.java)
            startActivity(tmpIntent)
        }

        val searchBtn = root.findViewById<ImageButton>(R.id.khdSearchButton)

        searchBtn.setOnClickListener {
            Toast.makeText(it.context, "go to search tab", Toast.LENGTH_SHORT).show()
            val tmpIntent = Intent(it.context, KHDSearchActivity::class.java)
            startActivity(tmpIntent)
        }

        // TODO :: RecylcerAdapter 달아줘야 함
        // DISCUSS :: ViewModel에서 View에서 받아온 이벤트에 대한 처리를 해야 하는데, bottomNavView의 2개의 파일을 viewmodel로 봐야 할지?
        //            아니면 viewmodel 파일을 제거하고 여기에 할 것인지, 지금까지는 제거해줘도 될 것 같다.

        return root
    }
}