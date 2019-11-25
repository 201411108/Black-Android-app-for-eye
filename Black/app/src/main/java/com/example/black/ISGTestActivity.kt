package com.example.black

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_tips_view.*
import kotlinx.android.synthetic.main.isg_test_activity.*

class ISGTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isg_test_activity)

        isgTestButton.setOnClickListener {
            //EyeSavingDatabase db = Room.databaseBuilder(getApplicationContext(), EyeSavingDatabase.class , "eyesaving").build()
            val db = Room.databaseBuilder(applicationContext, EyeSavingDatabase::class.java, "eyesaving").build()

            val insertImage : Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_foreground, null)
            var insertTestData = EyeSaving("orange", "Vitamin C", "good at eye", 2000, "orange is orange", insertImage, 0)
            db.EyeSavingDAO().insertData(insertTestData)

            val dataList = db.EyeSavingDAO().loadAllData()
            for(i in dataList){
                isgTestTextView.text = i.name + i.explain

            }

        }

        /*
        isgTestButton.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val eDB = database.getReference("eyesaving")

            //eDB.child("food").child("test").setValue(EyeSavingData("name", "element", "effect", 1234, "explain"))
            //setEyeSavingData(Category.food, "orange", EyeSavingData("orange", "Vitamin C", "good at Eye", 1000, "Orange is Orange", "src"))

            val eData = eDB.child("food").addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}
                override fun onDataChange(p0: DataSnapshot) {
                    val eDataStream = p0.getValue().toString() as Map<String, EyeSavingData?>
                    isgTestTextView.text = eDataStream.getValue("orange").toString()
                    //var eDataString = p0.getValue() as Map<String, EyeSavingData>
                    //val testMap = p0.getValue() as Map<String, EyeSavingData?>
                    //val testData = testMap.getValue("orange")
                    //isgTestTextView.text = testData.toString()
                        //val retData = eDataString.get("orange") //Get 이후 EyeSavingData 자료 다루는데서 오류 발생

                        //if(retData != null)
                        //    isgTestTextView.text = retData.name + retData.element
                        //else
                        //    isgTestTextView.text = "Cannot Find Data"


                }
            })
        }
         */
    }
}