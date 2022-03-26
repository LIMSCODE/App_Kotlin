package com.example.kotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var boardButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boardButton = findViewById(R.id.Board)

        boardButton.setOnClickListener {
            startActivity(Intent(this, BoardListActivity::class.java))
        }
    }
}