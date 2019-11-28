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

class EyeDataClass(var value : Map<String, Map<String, Map<String, Any>>>?) {

    /* 눈에 좋은 음식 정보 */
    // ===========================================================================================================================
    inner class EyeFoodInfo(val name : String, val element : String, val effect : String, val cost : String, val explain : String,
                            val imagePath : String)

    private var tmpEyeFoodInfo = arrayListOf<EyeFoodInfo>()

    private fun getEyeFood(foodInfo : ArrayList<EyeFoodInfo>) : ArrayList<EyeFoodInfo> {
        val dbKey = "food"
        val itemSet = value!!.getValue(dbKey).keys

        itemSet?.forEach {
            val itemName = it
            val itemInfo = value?.getValue(dbKey)?.getValue(itemName)
            val name = itemInfo?.getValue("name").toString()
            val element = itemInfo?.getValue("element").toString()
            val effect = itemInfo?.getValue("effect").toString()
            val cost = itemInfo?.getValue("cost").toString()
            val explain = itemInfo?.getValue("explain").toString()
            val imagePath = itemName + ".jpg"

            foodInfo.add(EyeFoodInfo(name, element, effect, cost, explain, imagePath))
        }

        return foodInfo
    }

    val eyeFoodInfo = getEyeFood(tmpEyeFoodInfo)
    // ===========================================================================================================================

    /* 눈에 좋은 차 정보 */
    // ===========================================================================================================================
    inner class EyeTeaInfo(val name : String, val element : String, val effect : String, val cost : String, val explain : String,
                           val imagePath : String)

    private val tmpEyeTeaInfo = arrayListOf<EyeTeaInfo>()

    private fun getEyeTea(teaInfo : ArrayList<EyeTeaInfo>) : ArrayList<EyeTeaInfo> {
        val dbKey = "tea"
        val itemSet = value!!.getValue(dbKey).keys

        itemSet?.forEach {
            val itemName = it
            val itemInfo = value?.getValue(dbKey)?.getValue(itemName)
            val name = itemInfo?.getValue("name").toString()
            val element = itemInfo?.getValue("element").toString()
            val effect = itemInfo?.getValue("effect").toString()
            val cost = itemInfo?.getValue("cost").toString()
            val explain = itemInfo?.getValue("explain").toString()
            val imagePath = itemName + ".jpg"

            teaInfo.add(EyeTeaInfo(name, element, effect, cost, explain, imagePath))
        }

        return teaInfo
    }

    val eyeTeaInfo = getEyeTea(tmpEyeTeaInfo)
    // ===========================================================================================================================


    inner class EyeDrug(val id : Int, val title : String, val content : String)
    inner class EyeExcercise(val id : Int, val title : String, val content : String)
    inner class EyeInfo(val id : Int, val title : String, val content : String)

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



}