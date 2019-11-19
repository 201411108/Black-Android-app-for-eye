package cs.smu.ac.embeddedsoftware

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cs.smu.ac.embeddedsoftware.R
import kotlinx.android.synthetic.main.kkm_study_insert_activity.*

class KkmStudyInsertActivity : AppCompatActivity(){


    var data = arrayOf("테스트1", "테스트2","테스트3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kkm_study_insert_activity)

        var majorAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)

        kkm_study_insert_activity_major.adapter = majorAdapter
        kkm_study_insert_activity_major.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 -> {
                        Toast.makeText(applicationContext,"0번",Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(applicationContext,"1번",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }




    }
}