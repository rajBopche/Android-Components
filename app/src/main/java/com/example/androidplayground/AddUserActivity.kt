package com.example.androidplayground

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidplayground.utility.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
    }

    fun onSaveButtonClicked(view: View) {
        if (view is Button) {
            val userName = edt_user_name.text.toString()
            val userType = edt_user_type.text.toString()

            if (userName.isNotBlank() && userType.isNotBlank()) {
                val intent = Intent().apply {
                    putExtra(Constants.EXTRA_USER_NAME, userName)
                    putExtra(Constants.EXTRA_USER_TYPE, userType)
                }

                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                showSnackBar("Details Cannot Be Empty")
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar
            .make(cl_add_user_container, message, Snackbar.LENGTH_LONG)
            .setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
            .setAction("DISMISS") {
                // dismiss
            }
            .show()
    }
}
