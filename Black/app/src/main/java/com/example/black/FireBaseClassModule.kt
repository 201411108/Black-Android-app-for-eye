package com.example.black

import android.graphics.drawable.Drawable
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//폐기


/*
 *  DB 내부 구조
 *  eyesaving (0: food, 1: tea ...)
 *  ㄴ food
 *      |
 *      key : String (name, key)
 *      ㄴ   name : String (이름)
 *      ㄴ   element: String(성분 예를들면 비타민C 같은거)
 *      ㄴ   effect: String (효과 이 음식이 어떻게 효과를 가져다 주는지)
 *      ㄴ   cost: Int (대략적인 평균 가격대)
 *      ㄴ   explain: String (해당 음식에 대한설명)
 *      ㄴ   image: Drawable
 *  ㄴ tea
 *      |
 *      이하구조는 food와 동일
 *  ㄴ etc... (이후로 몸에좋은 약 등등은 이름 명명해서 추후 업데이트하면 됨)
 */

class EyeSavingData(
    var name: String?,
    var element: String?,
    var effect: String?,
    var cost: String?,
    var explain: String?
    //var image: Drawable?
)

enum class Category {
    food, tea, pill, exercise, tip
}

/*
 *  함수명 : getEyeSavingData (카테고리(food, tea 등), 카테고리 내 음식 이름)
 *  반환값 : EyeSavingData 또는 null
 *  설명 : name 파라미터를 넣지 않고 호출할 경우 카테고리 내 전체 데이터를 EyeSavingData class 형태로 반환하고,
 *         name 파라미터에 값을 넣을 경우 카테고리 내 해당 이름의 데이터를 EyeSavingData 형태로 반환합니다.
 *  Tips : getEyeSavingData 를 호출할 때 파라미터는 Category.food, Category.tea 등을 사용하면 편리합니다.
 *  사용예시 : getEyeSavingData(Category.food, "orange")   // food 카테고리 내 orange 음식의 데이터를 반환합니다.
 */

/*
public fun getEyeSavingData(categoryEnum: Category, name: String) : EyeSavingData? {
    var category: String?
    when(categoryEnum){
        Category.food -> category = "food"
        Category.tea -> category = "tea"
    }
    var userData = FirebaseDatabase.getInstance().getReference("eyesaving")
    var retData: EyeSavingData? = null

    val eData = userData.child(category).addListenerForSingleValueEvent(object: ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {}
        override fun onDataChange(p0: DataSnapshot) {
            var eDataString = p0.getValue() as Map<String, EyeSavingData?>
            retData = eDataString.get(name) //Get 이후 EyeSavingData 자료 다루는데서 오류 발생
        }
    })
    return retData
}


 */

/*
 *  함수명 : setEyeSavingData (카테고리(food, tea 등), 카테고리 내 음식 이름, 음식에 대한 데이터)
 *  반환값 : X
 *  설명 : database에 EyeSavingData를 삽입합니다.
 *  Tips : Category 파라미터는 Category.food, Category.tea 등을 사용하면 편리합니다.
 *  사용예시 : setEyeSavingData(Category.food, "orange", EyeSavingData("orange", "vitamin C", "효과", 1000, "설명"))   // food 카테고리 내 orange 음식의 데이터를 반환합니다.
 */
public fun setEyeSavingData(categoryEnum: Category, name: String, data: EyeSavingData){
    val database = FirebaseDatabase.getInstance().getReference("eyesaving")
    var category: String?
    when(categoryEnum){
        Category.food -> category = "food"
        Category.tea -> category = "tea"
        Category.pill -> category = "pill"
        Category.exercise -> category = "exercise"
        Category.tip -> category = "tip"
    }
    database.child(category).child(name).setValue(data)
}
