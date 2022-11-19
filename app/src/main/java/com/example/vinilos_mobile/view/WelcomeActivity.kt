package com.example.vinilos_mobile.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.model.repository.VinilosRepository

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        var vinilosRepositoryinilosRepository = VinilosRepository(this.application)

    }

}