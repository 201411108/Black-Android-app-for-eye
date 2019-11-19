/*
 * 6. KHDDashboardSearchView.kt
 * 게시판 글을 검색할 수 있는 기능을 제공하는 activity
 */

package cs.smu.ac.embeddedsoftware

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.smu.ac.embeddedsoftware.ui.dashboard.dashboardRecyclerAdapter
import kotlinx.android.synthetic.main.khd_dashboard_search.*

class KHDDashboardSearchView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.khd_dashboard_search)

        // 검색 시 내용에 대한 데이터를 viewmodel로 부터 불러온다.
        val dataViewModel = KHDDashboardSearchViewModel()

        val searchRecyclerView : RecyclerView = findViewById(R.id.searchRecyclerView)
        // NOTICE :: 최초 실행 시 검색 결과가 없다는 RecyclerView 표시
        searchRecyclerView.adapter = initailRecyclerView(dataViewModel)

        // 검색 버튼 클릭 시 발생하는 액션
        // RecyclerView 구현
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchBtn = findViewById<ImageButton>(R.id.searchBtn)
        // NOTICE :: 이후 검색 시 검색 결과를 반영한 Recyclerview 표시
        searchBtn.setOnClickListener {
            searchRecyclerView.adapter = searchRecyclerViewItem(dataViewModel, searchEditText.text.toString())
        }

        val lm = LinearLayoutManager(searchRecyclerView.context)
        searchRecyclerView.layoutManager = lm
        searchRecyclerView.setHasFixedSize(true)

    }

    // 최초 검색화면으로 넘어갈 시 검색 결과가 없다는 것을 보여주기 위한 함수
    fun initailRecyclerView(dataViewModel: KHDDashboardSearchViewModel) : dashboardRecyclerAdapter {
        return dashboardRecyclerAdapter(searchRecyclerView.context, dataViewModel.noResultMessage) {
                    // 이 경우 클릭 시 아무 일도 일어나지 않는다.
                    Toast.makeText(this, "결과 없음!", Toast.LENGTH_SHORT).show()
               }
    }

    // 입력된 검색어를 찾아서 해당하는 내용을 불러오는 함수.
    // 해당 경우에 대한 recyclerAdapter를 반환하여 붙여준다.
    fun searchRecyclerViewItem(dataViewModel: KHDDashboardSearchViewModel, param : String) : dashboardRecyclerAdapter {

        // 검색 결과를 가져와서 임시로 저장할 배열
        var searchResultItems : ArrayList<dashBoardClass> = arrayListOf()

        // 검색어가 없는 경우, 아무 결과도 보여주지 않는 항목
        if(param == "") {
            return dashboardRecyclerAdapter(searchRecyclerView.context, dataViewModel.noResultMessage) {
                       // 이 경우에도 아무 일도 일어나지 않는다.
                       Toast.makeText(this, "결과 없음!", Toast.LENGTH_SHORT).show()
                   }
        // 검색어가 있을 경우 해당 검색어에 맞는 내용들을 검색 결과 배열에 저장하고 viewmodel로 부터 불러온다.
        } else {

            for(i in 0 until dataViewModel.dataArray.size) {

                // 제목 또는 본문에 검색에를 포함하고 있을 경우 검색 결과 배열에 아이템 추가
                if(dataViewModel.dataArray[i].itemBody.contains(param) || dataViewModel.dataArray[i].itemName.contains(param))
                    searchResultItems.add(dataViewModel.dataArray[i])

                else
                    continue

            }
            return dashboardRecyclerAdapter(searchRecyclerView.context, searchResultItems) {
                // 이 경우 해당 게시글 상세 정보로 이동한다.
                Toast.makeText(this, "제목 : ${it.itemName}, 내용 : ${it.itemBody}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}