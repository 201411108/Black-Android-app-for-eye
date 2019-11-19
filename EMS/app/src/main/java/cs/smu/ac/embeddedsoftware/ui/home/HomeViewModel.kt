package cs.smu.ac.embeddedsoftware.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel은 View의 정보를 가지고 있다. 화면이 바뀌더라도 변경되지 않는다.
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // TODO :: RecyclerView에 들어가는 DB 정보를 입력해야 한다.


}
