/*
 * EyeDataClass.kt
 * @handongkim
 * Firebase 도입 이전 테스트를 위한 ViewModel 클래스
 * history
 * 20191123     handongkim      init
 * 20191127     handongkim      테스트 데이터 추가
 * 20191128     handongkim      db 연동 성공
 */

package com.example.black

import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.isg_test_activity.*

class EyeDataClass(var value : Map<String, Map<String, Map<String, Any>>>?) {


    inner class testClass(val name : String, val element : String, val effect : String, val cost : String, val explain : String,
                          val imagePath : String)
    // TODO :: DB 구조에 맞게 inner class 변형
    inner class EyeFood(val id : Int, val title : String, val content : String)
    inner class EyeTea(val id : Int, val title : String, val content : String)
    inner class EyeDrug(val id : Int, val title : String, val content : String)
    inner class EyeExcercise(val id : Int, val title : String, val content : String)
    inner class EyeInfo(val id : Int, val title : String, val content : String)

    // TODO :: getter 함수 따로 구현
    val eyeFoodInfo = arrayListOf(EyeFood(1, "눈음식 1", "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다.\n" +
                                                                        "눈음식 1은 눈에 1하게 좋다."),
                                  EyeFood(2, "눈음식 2", "눈음식 2은 눈에 2하게 좋다."),
                                  EyeFood(3, "눈음식 3", "눈음식 3은 눈에 3하게 좋다."),
                                  EyeFood(4, "눈음식 4", "눈음식 4은 눈에 4하게 좋다."),
                                  EyeFood(5, "눈음식 5", "눈음식 5은 눈에 5하게 좋다."))

    val eyeTeaInfo = arrayListOf(EyeTea(1, "눈 차 1", "눈 차 1은 눈에 1하게 좋다."),
                                 EyeTea(2, "눈 차 2", "눈 차 2은 눈에 2하게 좋다."),
                                 EyeTea(3, "눈 차 3", "눈 차 3은 눈에 3하게 좋다."),
                                 EyeTea(4, "눈 차 4", "눈 차 4은 눈에 4하게 좋다."),
                                 EyeTea(5, "눈 차 5", "눈 차 5은 눈에 5하게 좋다."))

    val eyeDrugInfo = arrayListOf(EyeDrug(1, "눈 약 1", "눈 약 1은 눈에 1하게 좋다."),
                                  EyeDrug(2, "눈 약 2", "눈 약 2은 눈에 2하게 좋다."),
                                  EyeDrug(3, "눈 약 3", "눈 약 3은 눈에 3하게 좋다."),
                                  EyeDrug(4, "눈 약 4", "눈 약 4은 눈에 4하게 좋다."),
                                  EyeDrug(5, "눈 약 5", "눈 약 5은 눈에 5하게 좋다."))

    val eyeExcerciseInfo = arrayListOf(EyeExcercise(1, "눈 운동 1", "눈 운동 1은 눈에 1하게 좋다."),
                                       EyeExcercise(2, "눈 운동 2", "눈 운동 2은 눈에 2하게 좋다."),
                                       EyeExcercise(3, "눈 운동 3", "눈 운동 3은 눈에 3하게 좋다."),
                                       EyeExcercise(4, "눈 운동 4", "눈 운동 4은 눈에 4하게 좋다."),
                                       EyeExcercise(5, "눈 운동 5", "눈 운동 5은 눈에 5하게 좋다."))

    val eyeInfo = arrayListOf(EyeInfo(1, "눈 상식 1", "눈 상식 1은 눈에 1하게 좋다."),
                              EyeInfo(2, "눈 상식 2", "눈 상식 2은 눈에 2하게 좋다."),
                              EyeInfo(3, "눈 상식 3", "눈 상식 3은 눈에 3하게 좋다."),
                              EyeInfo(4, "눈 상식 4", "눈 상식 4은 눈에 4하게 좋다."),
                              EyeInfo(5, "눈 상식 5", "눈 상식 5은 눈에 5하게 좋다."))


    // FireBase data 가져와서 class에 넣어서 지금처럼 사용할 수 있도록
//    val database = FirebaseDatabase.getInstance()
//    val eDB = database.getReference("eyesaving")
//    var value : Map<String, Map<String, Map<String, Any>>>? = null
//
//    val eDataTest = eDB.addValueEventListener(object: ValueEventListener {
//
//        override fun onCancelled(p0: DatabaseError) { }
//
//        override fun onDataChange(p0: DataSnapshot) {
//            value = p0.getValue() as Map<String, Map<String, Map<String, Any>>>
//        }
//    })

    var dbKey = "food"
//
//    // 해당 데이터베이스에 속하는 item키들을 가져온다.
    val itemSet = value?.getValue(dbKey)?.keys
//
//    inner class testClass(val name : String, val element : String, val effect : String, val cost : String, val explain : String,
//                          val imagePath : String)
    var testInfo = arrayListOf<testClass>()

    private fun makeClassArray(itemSet: Set<String>?, testInfo: ArrayList<testClass>): ArrayList<testClass> {

        itemSet?.forEach {
            val itemName = it
            val itemInfo = value?.getValue(dbKey)?.getValue(itemName)
            val name = itemInfo?.getValue("name").toString()
            val element = itemInfo?.getValue("element").toString()
            val effect = itemInfo?.getValue("effect").toString()
            val cost = itemInfo?.getValue("cost").toString()
            val explain = itemInfo?.getValue("explain").toString()
            val imagePath = itemName + ".jpg"

            testInfo.add(testClass(name, element, effect, cost, explain, imagePath))
        }

        return testInfo
    }

    val testResultInfo = makeClassArray(itemSet, testInfo)
    /*
    val val2 = value?.getValue(cName)?.getValue(keyET.text.toString())
     * 이름 : nameET.setText(val2?.getValue("name").toString())
     * 영양성분 : elementET.setText(val2?.getValue("element").toString())
     * 효능 : effectET.setText(val2?.getValue("effect").toString())
     * 가격 : costET.setText(val2?.getValue("cost").toString())
     * 설명 : explainET.setText(val2?.getValue("explain").toString())
     */

//    val dRef = FirebaseStorage.getInstance().getReference()
//    var imagePath = keyET.text.toString() + ".jpg"
//    val dImage = dRef.child(imagePath)

    /* 받아오는 액티비티에서 사용
    val test = dImage.downloadUrl.addOnSuccessListener {
        Glide.with(this).load(it).into(isgTestImage)
    }.addOnFailureListener{
        isgTestImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.mipmap.ic_launcher_round, null))
    }
     */
}