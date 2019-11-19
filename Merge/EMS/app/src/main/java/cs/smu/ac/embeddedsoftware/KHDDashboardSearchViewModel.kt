/*
 * 7. KHDDashboardSearchViewModel.kt
 * 검색 화면의 정보를 보여주기 위한 viewmodel
 * dashboard 패키지에 있는 DashboardViewModel로 통합 가능할 것 같다.
 */

package cs.smu.ac.embeddedsoftware

import androidx.lifecycle.ViewModel

class KHDDashboardSearchViewModel : ViewModel() {

    // 아무 검색 결과를 받지 못했을 때 보여주는 변수
    var noResultMessage = arrayListOf(dashBoardClass("검색 결과가 없습니다.", ""))

    var dataArray = arrayListOf(
        // textView에서 4줄 까지 표시 가능. 정해진 크기 만큼 보이게 설정되어 있다.
        dashBoardClass("title 1", "content 1"),
        dashBoardClass("eltit 2", "content 2"),
        dashBoardClass("title 3", "ontentc 3"),
        dashBoardClass("title 4", "content 4"),
        dashBoardClass("eltit 5", "ontentc 5"),
        dashBoardClass("eltit 6", "content 6"),
        dashBoardClass("title 7", "ontentc 7"),
        dashBoardClass("eltit 8", "ontentc 8"),
        dashBoardClass("title 9", "content 9"),
        dashBoardClass("eltit 10", "content 10")
    )

}