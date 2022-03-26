package com.example.kotlinproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinproject.model.FirebaseId
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class RegisterActivity : AppCompatActivity() {
    //auth
    private val auth = FirebaseAuth.getInstance() // firebase auth 객체
 
    //파이어스토어
    private val mStore = FirebaseFirestore.getInstance()

    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var passwordCheck : EditText
    lateinit var name : EditText
    lateinit var phone : EditText
    lateinit var registerButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //입력값 받아오기
        email = findViewById(R.id.userEmail)
        password = findViewById(R.id.userPassword)
        passwordCheck = findViewById(R.id.userPasswordCheck)
        name = findViewById(R.id.userName)
        phone = findViewById(R.id.userPhone)

        registerButton = findViewById(R.id.register)

        registerButton.setOnClickListener {

            val emailStr = email.text.toString()
            val pwStr = password.text.toString()
            val pwCheckStr = passwordCheck.text.toString()
            val nameStr = name.text.toString()
            val phoneStr = phone.text.toString()
            //가입 정보 가져오기

            if (emailStr.isNotEmpty() && pwStr.isNotEmpty()) {

                //create (회원가입)
                auth.createUserWithEmailAndPassword(emailStr, pwStr)
                    ?.addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {

                            //파이어베이스에서 변수 가져오기
                            val user = auth.currentUser
                            if (user != null) {

                                var userMap = HashMap<String, Any>()

                                userMap.put(FirebaseId.documentId, user.getUid())
                                userMap.put(FirebaseId.name, nameStr)
                                userMap.put(FirebaseId.email, emailStr)
                                userMap.put(FirebaseId.password, pwStr)

                                mStore.collection(FirebaseId.user)
                                      .document(user.getUid()).set(userMap, SetOptions.merge())
                                finish()

                            } else {
                                Toast.makeText(this, "회원가입에러", Toast.LENGTH_SHORT).show()
                            }

                            Toast.makeText(this, "계정 생성 완료.", Toast.LENGTH_SHORT).show()
                            finish() // 가입창 종료

                        } else {
                            Toast.makeText(this, "계정 생성 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}