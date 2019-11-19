package cs.smu.ac.embeddedsoftware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Firebase Google Login
    lateinit var auth : FirebaseAuth
    lateinit var authListener : FirebaseAuth.AuthStateListener
    lateinit var googleSigneInClient : GoogleSignInClient //구글 로그인을 관리하는 클래스

    //DB
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("user/id2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configure Google Sign In
        //GoogleSignInOptions 옵션을 관리해주는 클래스로 API 키값과 요청할 값이 저장되어 있다.
        var gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();

        googleSigneInClient= GoogleSignIn.getClient(this,gso)
        //GoogleSignInClient : Google Login을 관리하는 클래스
        //GoogleSignInOption : 옵션을 관리해주는 클래스로 API키 값과 요청할 값이 저장되어 있다.


        googleLoginBtn.setOnClickListener(){
            signIn()
        }

        emailJoinBtn.setOnClickListener(){
            var id : String = edit_email.text.toString()
            var pw: String = edit_password.text.toString()
            myRef.setValue("isg1032", "id2")
            database.getReference("user").child("id3").setValue("isg1033")
        }

        emailLoginBtn.setOnClickListener() {
            val nextIntent = Intent(this, KHDStudyListActivity::class.java)
            startActivity(nextIntent)
        }


    }

    private fun signIn(){
        val signInIntent =googleSigneInClient.signInIntent
        startActivityForResult(signInIntent,100)
    }

    private fun logout(){
        FirebaseAuth.getInstance().signOut()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if(result.isSuccess){
                //구글 로그인이 성공했을 경우
                val account =result.signInAccount
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)

                //val mainFormActivity = Intent(this, MainFormActivity::class.java)
                //startActivity(mainFormActivity)
            }
        }
    }



}
