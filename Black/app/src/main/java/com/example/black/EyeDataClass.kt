package com.example.black

import androidx.lifecycle.ViewModel

class EyeDataClass : ViewModel() {

    inner class eyeFood(val id:Int, val title:String, val content:String)
    //inner class eyeTea(val id:Int, val title:String, val content:String)
    //inner class eyeDrug(val id:Int, val title:String, val content:String)
    //inner class eyeExcercise(val id:Int, val title:String, val content:String)
    //inner class eyeInfo(val id:Int, val title:String, val content:String)

    // TODO :: Firebase를 활용한 쿼리로 추후 변경
    val eyeFoodInfo = arrayListOf(eyeFood(1, "눈음식 1", "눈음식 1은 눈에 1하게 좋다."),
                                  eyeFood(2, "눈음식 2", "눈음식 2은 눈에 2하게 좋다."),
                                  eyeFood(3, "눈음식 3", "눈음식 3은 눈에 3하게 좋다."),
                                  eyeFood(4, "눈음식 4", "눈음식 4은 눈에 4하게 좋다."),
                                  eyeFood(5, "눈음식 5", "눈음식 5은 눈에 5하게 좋다."))

}