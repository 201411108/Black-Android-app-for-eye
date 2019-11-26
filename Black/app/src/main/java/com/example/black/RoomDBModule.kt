package com.example.black

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.*


/*
 *  DB 내부 구조
 *  eyesaving (0: food, 1: tea ...)
 *  ㄴ food
 *      |
 *      key : int (increase Number, key)
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


//Room DB를 이용하여 RecyclerView 에 값 불러오기 : https://blog.yena.io/studynote/2018/09/08/Android-Kotlin-Room.html

@Entity(tableName = "eyesaving")
data class EyeSaving(@PrimaryKey val name: String,
                     val element: String,
                     val effect: String,
                     val cost: Int,
                     val explain: String,
                     //val image: Drawable?,
                     val category: Int) //0: food, 1: tea, 2: ...

@Dao
public interface EyeSavingDAO{

    /*
     *  onConflict를 Annotation은 Primary Key가 중복되는 내용이 있을경우
     *  어떻게 처리할지를 설정한다.(현재 REPLACE 로 설정)
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(vararg eyesaving: EyeSaving)

    @Delete
    fun deleteData(vararg eyesaving: EyeSaving)


    /*
     *  아래의 쿼리와 같이 @Query("쿼리내용") fun 함수명() : 반환값
     *  과 같이 사용하면 함수를 호출했을 때 쿼리에 대한 값을 반환받을 수 있다.
     *  EX) @Query("SELECT * FROM eyesaving WHERE category=0")
     */
    @Query("SELECT * FROM eyesaving")
    fun loadAllData(): List<EyeSaving>
}

@Database(entities = [(EyeSaving::class)], version = 1)
abstract class EyeSavingDatabase:RoomDatabase(){
    abstract fun EyeSavingDAO(): EyeSavingDAO

    companion object{
        private var INSTANCE: EyeSavingDatabase? = null

        fun getInstance(context: Context): EyeSavingDatabase?{
            if(INSTANCE == null){
                synchronized(EyeSavingDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        EyeSavingDatabase::class.java, "eyesaving")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}

class RoomDBModule{

}


//ANKO Library : https://github.com/Kotlin/anko/wiki/Anko-SQLite
