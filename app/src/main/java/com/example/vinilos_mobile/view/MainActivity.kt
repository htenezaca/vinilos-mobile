package com.example.vinilos_mobile.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.repository.VinilosRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}