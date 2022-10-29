package com.example.vinilos_mobile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.model.repository.VinilosRepository

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Log.d("aber","olam")

        var vinilosRepositoryinilosRepository = VinilosRepository()
        vinilosRepositoryinilosRepository.getCollectors(this.applicationContext, {
            Log.d("SUCCESS", it.toString())
        }) {
            Log.d("FAIL", it.toString())
        }
    }
}