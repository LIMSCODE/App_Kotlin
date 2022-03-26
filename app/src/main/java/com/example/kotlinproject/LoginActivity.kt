package com.example.kotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    //auth
    private val auth = FirebaseAuth.getInstance()

    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var loginButton : Button
    lateinit var registerButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //입력값 받아오기
        email = findViewById(R.id.loginEmail)
        password = findViewById(R.id.loginPassword)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.gotoRegister)

        //로그인 버튼 누름
        loginButton.setOnClickListener {

            val emailStr = email.text.toString()
            val pwStr = password.text.toString()

            if (emailStr.isNotEmpty() && pwStr.isNotEmpty()) {

                //SignIn (로그인)
                auth.signInWithEmailAndPassword(emailStr, pwStr)
                    ?.addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(baseContext, "로그인에 성공 하였습니다.", Toast.LENGTH_SHORT).show()

                            val user = auth.currentUser
                            if (user!= null) {
                                startActivity(Intent(this, MainActivity::class.java))
                            }

                        } else {
                            Toast.makeText(
                                baseContext, "로그인에 실패 하였습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        //회원가입 버튼 누름
        registerButton.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    // 유저정보 넘겨주고 메인 액티비티 호출
    //fun moveMainPage(user: FirebaseUser?){
    //    if( user!= null){
    //        startActivity(Intent(this,MainActivity::class.java))
    //        finish()
    //    }
    //}


}