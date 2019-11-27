/*
 * EyeDataClass.kt
 * @handongkim
 * Firebase 도입 이전 테스트를 위한 ViewModel 클래스
 * history
 * 20191123     handongkim      init
 * 20191127     handongkim      테스트 데이터 추가
 */

package com.example.black

class EyeDataClass {

    // TODO :: DB 구조에 맞게 inner class 변형
    inner class EyeFood(val id : Int, val title : String, val content : String)
    inner class EyeTea(val id : Int, val title : String, val content : String)
    inner class EyeDrug(val id : Int, val title : String, val content : String)
    inner class EyeExcercise(val id : Int, val title : String, val content : String)
    inner class EyeInfo(val id : Int, val title : String, val content : String)

    // TODO :: getter, setter 함수 따로 구현
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


}