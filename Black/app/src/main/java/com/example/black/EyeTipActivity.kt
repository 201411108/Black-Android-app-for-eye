/*
 * EyeTipActivity.kt
 * @handongkim
 * intent를 통해 어떤 DB와 연동되는지 확인 후 해당 데이터를 반영하는 액티비티
 * history
 * 20191123     handongkim      init
 * 20191124     handongkim      recyclerview item 클릭 기능 구현
 * 20191127     handongkim      누른 버튼에 따라 알맞는 recyclerViewAdapter 적용
 * 20191128     handongkim      DB 연동 성공
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_tips_view.*
import kotlin.random.Random

class EyeTipActivity : AppCompatActivity() {

    val IMAGEPATH = "IMAGEPATH"
    val NAME = "NAME"
    val COST = "COST"
    val ELEMENT = "ELEMENT"
    val EFFECT = "EFFECT"
    val EXPLAIN = "EXPLAIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_view)

        Toast.makeText(this, "데이터를 가져오려면 제목을 누르세요.", Toast.LENGTH_LONG).show()

        val database = FirebaseDatabase.getInstance()
        val eDB = database.getReference("eyesaving")
        var value: Map<String, Map<String, Map<String, Any>>>? = null

        eDB.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                value = p0.getValue() as Map<String, Map<String, Map<String, Any>>>
            }
        })

        val dbKey = intent.getStringExtra("DBKEY")
        val title = intent.getStringExtra("TITLE")

        tipTitle.text = title

        tipTitle.setOnClickListener {
            eDB.child("refresh").setValue(Random(100).toString())
             Log.d("DBOUTERTEST", value.toString())

            try {
                val eyeTipData = EyeDataClass(value)

                when(dbKey) {
                    "FOOD" -> {
                        val recyclerAdapter = FoodRecyclerAdapter(this, eyeTipData.eyeFoodInfo) {
                            val intent = Intent(this, eachTipActivity::class.java)
                            intent.putExtra(IMAGEPATH, it.imagePath)
                            intent.putExtra(NAME, it.name)
                            intent.putExtra(COST, it.cost)
                            intent.putExtra(ELEMENT, it.element)
                            intent.putExtra(EFFECT, it.effect)
                            intent.putExtra(EXPLAIN, it.explain)
                            startActivity(intent)
                        }
                        tipRecyclerView.adapter = recyclerAdapter
                    }
                    "TEA" -> {
                        val recyclerAdapter = TeaRecyclerAdapter(this, eyeTipData!!.eyeTeaInfo) {
                            val intent = Intent(this, eachTipActivity::class.java)
                            intent.putExtra(IMAGEPATH, it.imagePath)
                            intent.putExtra(NAME, it.name)
                            intent.putExtra(COST, it.cost)
                            intent.putExtra(ELEMENT, it.element)
                            intent.putExtra(EFFECT, it.effect)
                            intent.putExtra(EXPLAIN, it.explain)
                            startActivity(intent)
                        }
                        tipRecyclerView.adapter = recyclerAdapter
                    }
                }

                val lm = LinearLayoutManager(tipRecyclerView.context)
                tipRecyclerView.layoutManager = lm
                tipRecyclerView.setHasFixedSize(true)

            } catch (e : NullPointerException) {
//                Toast.makeText(this, "데이터 가져오기를 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
//
//                val intent = Intent(this, EyeTipSelectActivity::class.java)
//                startActivity(intent)

            } finally {
                eDB.child("refresh").setValue(Random(100).toString())

                val eyeTipData = EyeDataClass(value)

                when(dbKey) {
                    "FOOD" -> {
                        val recyclerAdapter = FoodRecyclerAdapter(this, eyeTipData.eyeFoodInfo) {
                            val intent = Intent(this, eachTipActivity::class.java)
                            intent.putExtra(IMAGEPATH, it.imagePath)
                            intent.putExtra(NAME, it.name)
                            intent.putExtra(COST, it.cost)
                            intent.putExtra(ELEMENT, it.element)
                            intent.putExtra(EFFECT, it.effect)
                            intent.putExtra(EXPLAIN, it.explain)
                            startActivity(intent)
                        }
                        tipRecyclerView.adapter = recyclerAdapter
                    }
                    "TEA" -> {
                        val recyclerAdapter = TeaRecyclerAdapter(this, eyeTipData!!.eyeTeaInfo) {
                            val intent = Intent(this, eachTipActivity::class.java)
                            intent.putExtra(IMAGEPATH, it.imagePath)
                            intent.putExtra(NAME, it.name)
                            intent.putExtra(COST, it.cost)
                            intent.putExtra(ELEMENT, it.element)
                            intent.putExtra(EFFECT, it.effect)
                            intent.putExtra(EXPLAIN, it.explain)
                            startActivity(intent)
                        }
                        tipRecyclerView.adapter = recyclerAdapter
                    }
                }

                val lm = LinearLayoutManager(tipRecyclerView.context)
                tipRecyclerView.layoutManager = lm
                tipRecyclerView.setHasFixedSize(true)
            }

        } // end of onClickListener

//        when (dbKey) {
//            "FOOD" -> {
//                    val recyclerAdapter = FoodRecyclerAdapter(this, eyeTipData.eyeFoodInfo) {
//                    val intent = Intent(this, eachTipActivity::class.java)
//                    intent.putExtra("id", it.id.toString())
//                    intent.putExtra("title", it.title)
//                    intent.putExtra("content", it.content)
//                    intent.putExtra("title", it.title)
//                    startActivity(intent)
//
//                }
//                tipRecyclerView.adapter = recyclerAdapter
//            }
//            "TEA" -> {
//                val recyclerAdapter = TeaRecyclerAdapter(this, eyeTipData.eyeTeaInfo) {
//                    val intent = Intent(this, eachTipActivity::class.java)
//                    intent.putExtra("id", it.id.toString())
//                    intent.putExtra("title", it.title)
//                    intent.putExtra("content", it.content)
//                    startActivity(intent)
//
//                }
//                tipRecyclerView.adapter = recyclerAdapter
//            }
//            "DRUG" -> {
//                val recyclerAdapter = DrugRecyclerAdapter(this, eyeTipData.eyeDrugInfo) {
//                    val intent = Intent(this, eachTipActivity::class.java)
//                    intent.putExtra("id", it.id.toString())
//                    intent.putExtra("title", it.title)
//                    intent.putExtra("content", it.content)
//                    startActivity(intent)
//
//                }
//                tipRecyclerView.adapter = recyclerAdapter
//            }
//            "EXCERCISE" -> {
//                val recyclerAdapter = ExcerciseRecyclerAdapter(this, eyeTipData.eyeExcerciseInfo) {
//                    val intent = Intent(this, eachTipActivity::class.java)
//                    intent.putExtra("id", it.id.toString())
//                    intent.putExtra("title", it.title)
//                    intent.putExtra("content", it.content)
//                    startActivity(intent)
//
//                }
//                tipRecyclerView.adapter = recyclerAdapter
//            }
//            "INFO" -> {
//                val recyclerAdapter = InfoRecyclerAdapter(this, eyeTipData.eyeInfo) {
//                    val intent = Intent(this, eachTipActivity::class.java)
//                    intent.putExtra("id", it.id.toString())
//                    intent.putExtra("title", it.title)
//                    intent.putExtra("content", it.content)
//                    startActivity(intent)
//
//                }
//                tipRecyclerView.adapter = recyclerAdapter
//            }
//        }

    } // end of onCreate

} // end of class