/*
 * EyeDataClass.kt
 * @handongkim
 * Firebase 도입 이전 테스트를 위한 ViewModel 클래스
 * history
 * 20191123     handongkim      init
 */

package com.example.black

import androidx.lifecycle.ViewModel

class EyeDataClass : ViewModel() {

    // TODO :: Firebase를 활용한 쿼리로 추후 변경
    val eyeFoodInfo = arrayListOf(eyeFood(1, "눈음식 1", "눈음식 1은 눈에 1하게 좋다."),
                                  eyeFood(2, "눈음식 2", "눈음식 2은 눈에 2하게 좋다."),
                                  eyeFood(3, "눈음식 3", "눈음식 3은 눈에 3하게 좋다."),
                                  eyeFood(4, "눈음식 4", "눈음식 4은 눈에 4하게 좋다."),
                                  eyeFood(5, "눈음식 5", "눈음식 5은 눈에 5하게 좋다."))

    val eyeTeaInfo = arrayListOf(eyeTea(1, "눈 차 1", "눈 차 1은 눈에 1하게 좋다."),
                                 eyeTea(2, "눈 차 2", "눈 차 2은 눈에 2하게 좋다."),
                                 eyeTea(3, "눈 차 3", "눈 차 3은 눈에 3하게 좋다."),
                                 eyeTea(4, "눈 차 4", "눈 차 4은 눈에 4하게 좋다."),
                                 eyeTea(5, "눈 차 5", "눈 차 5은 눈에 5하게 좋다."))


}