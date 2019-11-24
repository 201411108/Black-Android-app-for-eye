package com.example.black

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_tips_view.*
import kotlinx.android.synthetic.main.isg_test_activity.*

class ISGTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isg_test_activity)

        isgTestButton.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val eDB = database.getReference("eyesaving")

            //eDB.child("food").child("test").setValue(EyeSavingData("name", "element", "effect", 1234, "explain"))
            setEyeSavingData(Category.food, "orange", EyeSavingData("orange", "Vitamin C", "good at Eye", 1000, "Orange is Orange", "src"))


            val dTestVal = getEyeSavingData(Category.food, "orange")
            if(dTestVal != null)
                isgTestTextView.text = dTestVal.name + dTestVal.element //오류발생
            isgTestTextView.text = dTestVal.toString()
        }
    }
}