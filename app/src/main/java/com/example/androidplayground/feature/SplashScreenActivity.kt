package com.example.androidplayground.feature

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.androidplayground.R
import com.example.androidplayground.feature.users.UsersActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        goToUserScreen()
    }

    private fun goToUserScreen() {
        Handler().postDelayed({
            Intent(this, UsersActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }, 2000L)

    }
}
