package com.example.black

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_tips_view.*

class EyeTipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_view)

        tipTitle.text = intent.getStringExtra("key")

        val tipRecyclerView : RecyclerView = findViewById(R.id.tipRecyclerView)
        // TODO :: 넘겨받는 intent에 따라서 다른 결과를 가져오도록 만들어야 한다.
        val recyclerAdapter = recyclerViewAdapter(this, EyeDataClass().eyeFoodInfo) {
            Toast.makeText(this, "제목 : ${it.title}", Toast.LENGTH_SHORT).show()
        }

        tipRecyclerView.adapter = recyclerAdapter

        val lm = LinearLayoutManager(tipRecyclerView.context)
        tipRecyclerView.layoutManager = lm
        tipRecyclerView.setHasFixedSize(true)
    }
}