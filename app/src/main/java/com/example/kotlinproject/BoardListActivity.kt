package com.example.kotlinproject

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.model.UserData
import com.example.kotlinproject.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BoardListActivity : AppCompatActivity() {

    private lateinit var addingBtn : FloatingActionButton
    private lateinit var recv : RecyclerView
    private lateinit var userList : ArrayList<UserData>
    private lateinit var userAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)    //activity_main

        userList = ArrayList()
        userAdapter = UserAdapter(this, userList)

        //setRecycler view Adapter
        recv = findViewById(R.id.mRecycler)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter

        addingBtn = findViewById(R.id.addingBtn)
        //버튼누를시 제목, 내용 입력하는 창 뜬다.
        addingBtn.setOnClickListener {
            addInfo()
        }
    }


    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.write_post, null)   //add_item

        //set view
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") {
            dialog,_ ->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("Name : $names" , "Mobile No. : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding User Info Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        addDialog.setNegativeButton("Cancel") {
            dialog,_ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }

        addDialog.create()
        addDialog.show()
    }
}