/*
 * 3. DashboardFragment.kt
 * 네비게이션 바에서 dashboard 선택 시 뜨는 fragment, dashboard 액티비티라고 생각해도 됨.
 * DashboardViewModel에서 데이터를 받아와서 기능을 제공함.
 */

package cs.smu.ac.embeddedsoftware.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.smu.ac.embeddedsoftware.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 만들어진 viewmodel을 불러온다.
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        // 만든 옵션 메뉴 가져오기(우측 상단 검색 버튼 활성화를 위한 함수
        setHasOptionsMenu(true)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // RecyclerView 불러오기
        val dashBoardItemView : RecyclerView = root.findViewById(R.id.dashBoardItemView)
        val recyclerAdapter = dashboardRecyclerAdapter(dashBoardItemView.context, dashboardViewModel.dataArray) {
                // recyclerView의 각 item 클릭 시 일어나는 클릭 리스너, 람다식으로 구현.
                Toast.makeText(super.getContext(), "제목 : ${it.itemName}, 내용 : ${it.itemBody}", Toast.LENGTH_SHORT).show()
            }
        dashBoardItemView.adapter = recyclerAdapter

        val lm = LinearLayoutManager(dashBoardItemView.context)
        dashBoardItemView.layoutManager = lm
        dashBoardItemView.setHasFixedSize(true)
        // ==========================================================


        // 글쓰기 버튼 클릭 시 글쓰기 액티비티(KHDDashboardWriteView)로 전환됨.
        val writeBtn = root.findViewById<Button>(R.id.writeBtn)
        writeBtn.setOnClickListener {
            val nextIntent = Intent(super.getContext(), KHDDashboardWriteView::class.java)
            startActivity(nextIntent)
        }

        return root
    }

    // 상단 화면 표시를 위한 함수
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.dashboard_menu, menu)
    }

    // 각 메뉴바 버튼 클릭 시 일어나는 이벤트
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.dashboard_search -> {
                val nextIntent = Intent(context, KHDDashboardSearchView::class.java)
                startActivity(nextIntent)
                //Toast.makeText(super.getContext(), "검색 버튼 활성화", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}