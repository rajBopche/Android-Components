package com.example.androidplayground.feature.users

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidplayground.R
import com.example.androidplayground.utility.Constants
import kotlinx.android.synthetic.main.activity_detail.*

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportPostponeEnterTransition()
        loadUserImage()
    }

    private fun loadUserImage() {
        val userData = intent?.extras?.getSerializable(Constants.EXTRA_USER_DATA)
        Glide.with(this)
            .load((userData as UserData).avatarUrl)
            .placeholder(R.mipmap.ic_place_holder_round)
            .fallback(R.mipmap.ic_place_holder_round)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }
            }).into(iv_detail_avatar)
    }

    fun onDeleteButtonClicked(view: View) {
        if (view is Button) {
            showToast("Sorry, Not Implemented")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}