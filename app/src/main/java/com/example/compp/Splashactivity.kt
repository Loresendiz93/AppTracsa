package com.example.compp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splashactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,MainActivity::class.java))
    }
}