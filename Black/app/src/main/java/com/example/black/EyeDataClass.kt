/*
 * EyeDataClass.kt
 * @handongkim
 * Firebase 도입 이전 테스트를 위한 ViewModel 클래스
 * history
 * 20191123     handongkim      init
 * 20191127     handongkim      테스트 데이터 추가
 * 20191128     handongkim      db 연동 성공
 * 20191130     handongkim      통합 클래스로 모듈화
 */

package com.example.black

class EyeDataClass(var value : Map<String, Map<String, Map<String, Any>>>?) {

    inner class EyeInfo(val name : String, val element : String, val effect : String, val cost : String, val explain : String,
                            val imagePath : String)

    /* 눈에 좋은 음식 정보 */
    // ===========================================================================================================================

    private var tmpEyeFoodInfo = arrayListOf<EyeInfo>()

    private fun getEyeFood(foodInfo : ArrayList<EyeInfo>) : ArrayList<EyeInfo> {
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

            foodInfo.add(EyeInfo(name, element, effect, cost, explain, imagePath))
        }

        return foodInfo
    }

    val eyeFoodInfo = getEyeFood(tmpEyeFoodInfo)
    // ===========================================================================================================================

    /* 눈에 좋은 차 정보 */
    // ===========================================================================================================================

    private val tmpEyeTeaInfo = arrayListOf<EyeInfo>()

    private fun getEyeTea(teaInfo : ArrayList<EyeInfo>) : ArrayList<EyeInfo> {
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

            teaInfo.add(EyeInfo(name, element, effect, cost, explain, imagePath))
        }

        return teaInfo
    }

    val eyeTeaInfo = getEyeTea(tmpEyeTeaInfo)

    // ===========================================================================================================================


    /* 눈에 좋은 차 정보 */
    // ===========================================================================================================================

    private val tmpEyeDrugInfo = arrayListOf<EyeInfo>()

    private fun getEyeDrug(teaInfo : ArrayList<EyeInfo>) : ArrayList<EyeInfo> {
        val dbKey = "pill"
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

            teaInfo.add(EyeInfo(name, element, effect, cost, explain, imagePath))
        }

        return teaInfo
    }

    val eyeDrugInfo = getEyeDrug(tmpEyeDrugInfo)

    // ===========================================================================================================================

}