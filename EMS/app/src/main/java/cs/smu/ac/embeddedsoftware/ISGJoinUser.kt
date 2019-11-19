package cs.smu.ac.embeddedsoftware

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.isg_join_user.*
import kotlinx.android.synthetic.main.jhy_activity_myinfo.*

class ISGJoinUser : AppCompatActivity(){

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("user")

    val data : UserData? = UserData("ImSangKyun", "isg1031@naver.com", "abcd1234!", 201511051, "010-4741-7383")

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isg_join_user)

        confirmEmailButton.setOnClickListener {
            val emailString: String = editJoinId.text.toString()
            val eDataValue = myRef.child(emailString)



        }

    }
}
