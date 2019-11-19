/*
 * DashboardViewModel.kt
 * dashboard 아이템에 대한 정보를 불러오는 클래스로, 현재는 임의의 클래스 배열을 만들어둔 상태
 * 추후 firebase 이용 시 모든 데이터를 불러오는 메소드를 구현하여 사용할 예정.
 */

package cs.smu.ac.embeddedsoftware.ui.dashboard

import androidx.lifecycle.ViewModel
import cs.smu.ac.embeddedsoftware.dashBoardClass

// ViewModel 하나로 다른 Dashboard 관련 뷰에 데이터를 뿌려줄 수 있을까?
class DashboardViewModel : ViewModel() {

    // 기본적인 텍스트 설정 방법, 추후 확인해 볼 것
    //private val _text = MutableLiveData<String>().apply {
    //    value = "This is dashboard Fragment"
    //}
    //val text: LiveData<String> = _text

    var dataArray = arrayListOf<dashBoardClass>(
        // textView에서 4줄 까지 표시 가능. 정해진 크기 만큼 보이게 설정되어 있다.
        dashBoardClass("제목 1", "내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n내용이 얼마나 길어질 수 있을까? \n"),
        dashBoardClass("제목 2", "내용 2"),
        dashBoardClass("제목 3", "내용 3"),
        dashBoardClass("제목 4", "내용 4"),
        dashBoardClass("제목 5", "내용 5"),
        dashBoardClass("제목 6", "내용 6"),
        dashBoardClass("제목 7", "내용 7"),
        dashBoardClass("제목 8", "내용 8"),
        dashBoardClass("제목 9", "내용 9"),
        dashBoardClass("제목 10", "내용 10")
    )
}