package com.example.tabactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabHost1.setup()

        var ts1 = tabHost1.newTabSpec("Tap spec1")
        ts1.setContent(R.id.content1)
        ts1.setIndicator("TAB 1")
        tabHost1.addTab(ts1)

        var ts2 = tabHost1.newTabSpec("Tap spec2")
        ts2.setContent(R.id.content2)
        ts2.setIndicator("TAB 2")
        tabHost1.addTab(ts2)

        var ts3 = tabHost1.newTabSpec("Tap spec3")
        ts3.setContent(R.id.content3)
        ts3.setIndicator("TAB 3")
        tabHost1.addTab(ts3)



        //스터디 목록 샘플
        var list = ArrayList<StudyItem>()
        list.add(StudyItem(getDrawable(R.drawable.image01)!!, "title1", "content1" ))
        list.add(StudyItem(getDrawable(R.drawable.image01)!!, "title2", "content2" ))
        list.add(StudyItem(getDrawable(R.drawable.image01)!!, "title3", "content3" ))
        list.add(StudyItem(getDrawable(R.drawable.image01)!!, "title4", "content4" ))
        list.add(StudyItem(getDrawable(R.drawable.image01)!!, "title5", "content5" ))

        val adapter = StudyRecyclerAdapter(list)
        kkm_main_recyclerview.adapter = adapter
        kkm_main_recyclerview.layoutManager = LinearLayoutManager(this)


        //검색조건 목록 샘플
        var conditionList = ArrayList<ConditionItem>()
        conditionList.add(ConditionItem("condition1"))
        conditionList.add(ConditionItem("condition2"))
        conditionList.add(ConditionItem("condition3"))
        conditionList.add(ConditionItem("condition4"))
        conditionList.add(ConditionItem("condition5"))

        val conditionAdapter = ConditionRecyclerAdapter(conditionList)
        kkm_main_condition_recycler.adapter = conditionAdapter
        kkm_main_condition_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        studySearchButton.setOnClickListener {
            val intent = Intent(this, KkmStudyInsertActivity::class.java)
            startActivity(intent)
        }

        studyInsertButton.setOnClickListener{
            val intent = Intent(this, KkmStudyCreateActivity::class.java)
            startActivity(intent)
        }
    }
}
