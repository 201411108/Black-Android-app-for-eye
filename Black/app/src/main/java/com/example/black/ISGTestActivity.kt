package com.example.black

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.accessibility.AccessibilityViewCommand
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_tips_view.*
import kotlinx.android.synthetic.main.isg_test_activity.*
import kotlin.random.Random

class ISGTestActivity : AppCompatActivity() {


    //private var eyesavingDB: EyeSavingDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isg_test_activity)

        RBgroup.check(foodRB.id)

        /* ROOM

        eyesavingDB = EyeSavingDatabase.getInstance(this)
        //val db = Room.databaseBuilder(applicationContext, EyeSavingDatabase::class.java, "eyesaving").allowMainThreadQueries().build()

        isgTestButton.setOnClickListener {
            //EyeSavingDatabase db = Room.databaseBuilder(getApplicationContext(), EyeSavingDatabase.class , "eyesaving").build()

            val insertImage : Drawable? = ResourcesCompat.getDrawable(resources, R.mipmap.ic_launcher_round, null)
            //var insertTestData = EyeSaving("orange", "Vitamin C", "good at eye", 2000, "orange is orange", 0)
            var insertTestData = EyeSaving("orange", "Vitamin C", "good at eye", 2000, "orange is orange", insertImage, 0)

            eyesavingDB?.EyeSavingDAO()?.insertData(insertTestData)

        }


        isgTestButton2.setOnClickListener {
            val dataList = eyesavingDB?.EyeSavingDAO()?.loadAllData()
            if (dataList != null) {
                for(i in dataList){
                    //isgTestImage.setImageDrawable(i.image)
                    isgTestTextView.text = i.name + i.explain
                }
            }
        }
      ROOM */


        //Image
        /*
        val dRef = FirebaseStorage.getInstance().getReference()
        val dBlueberryImage = dRef.child("blueberry.jpg")
        val test = dBlueberryImage.downloadUrl.addOnSuccessListener {
            Glide.with(this).load(it).into(imgTestView)
        }.addOnFailureListener{
            imgTestView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.mipmap.ic_launcher_round, null))
        }

         */


        // Database

        val database = FirebaseDatabase.getInstance()
        val eDB = database.getReference("eyesaving")
        var value: Map<String, Map<String, Map<String, Any>>>? = null
        var cName: String = "food"

        val eDataTest = eDB.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                isgTestTextView.text = p0.toException().toString()
            }
            override fun onDataChange(p0: DataSnapshot) {
                value = p0.getValue() as Map<String, Map<String, Map<String, Any>>>
                val value2 = value!!.getValue(cName).keys
                isgTestTextView.text = "key is $value2"
            }
        })

        RBgroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                when(RBgroup.checkedRadioButtonId){
                    foodRB.id -> cName = "food"
                    teaRB.id -> cName = "tea"
                    pillRB.id -> cName = "pill"
                    exerciseRB.id -> cName = "exercise"
                    tipRB.id -> cName = "tip"
                }
                eDB.child("refresh").setValue(Random(10).toString())
            }
        )

        isgTestButton.setOnClickListener {
            //eDB.child("food").child("test").setValue(EyeSavingData("name", "element", "effect", 1234, "explain", resources.getDrawable(R.mipmap.ic_launcher_round)))
            var categoryName: Category = Category.food
            when(RBgroup.checkedRadioButtonId){
                foodRB.id -> categoryName = Category.food
                teaRB.id -> categoryName = Category.tea
                pillRB.id -> categoryName = Category.pill
                exerciseRB.id -> categoryName = Category.exercise
                tipRB.id -> categoryName = Category.tip
            }

            var eData: EyeSavingData = EyeSavingData(nameET.text.toString(), elementET.text.toString(), effectET.text.toString(), costET.text.toString(), explainET.text.toString())
            //setEyeSavingData(Category.food, "test", EyeSavingData("test", "Vitamin C", "good at Eye", "1000", "TEST"))
            setEyeSavingData(categoryName, keyET.text.toString(), eData)

            keyET.text.clear()
            nameET.text.clear()
            elementET.text.clear()
            effectET.text.clear()
            costET.text.clear()
            explainET.text.clear()
            /*

             */
        }

        isgTestButton2.setOnClickListener {
            if(keyET.text.isEmpty())
                Toast.makeText(this, "Please Input Key Value", Toast.LENGTH_LONG).show()
            else{
                val val2 = value?.getValue(cName)?.getValue(keyET.text.toString())
                nameET.setText(val2?.getValue("name").toString())
                elementET.setText(val2?.getValue("element").toString())
                effectET.setText(val2?.getValue("effect").toString())
                costET.setText(val2?.getValue("cost").toString())
                explainET.setText(val2?.getValue("explain").toString())

                val dRef = FirebaseStorage.getInstance().getReference()
                var imagePath = keyET.text.toString() + ".jpg"
                val dImage = dRef.child(imagePath)
                val test = dImage.downloadUrl.addOnSuccessListener {
                    Glide.with(this).load(it).into(isgTestImage)
                }.addOnFailureListener{
                    isgTestImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.mipmap.ic_launcher_round, null))
                }
            }
        }



    }
}