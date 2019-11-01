package com.example.androidplayground.feature.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidplayground.R
import com.example.androidplayground.utility.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_users.*


class UsersActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        getloadingStatus()
        getData()
    }

    private fun setRecyclerView(userList: List<UserData>) {
        val userAdapter = UserListAdapter(this, userList)
        rv_data_list.setHasFixedSize(true)
        rv_data_list.adapter = userAdapter
    }

    private fun getData() {
        userViewModel.getData()
        userViewModel.userList.observe(this, Observer {
            if (it != null) {
                setRecyclerView(it)
            }
        })
    }


    private fun getloadingStatus() {
        userViewModel.getLoadingStatus().observe(this, Observer {
            when (it) {
                Constants.STATUS_COMPLETE -> {
                    pb_progress.visibility = View.GONE
                }
                Constants.STATUS_LOADING -> {
                    pb_progress.visibility = View.VISIBLE
                }
                Constants.STATUS_ERROR -> {
                    pb_progress.visibility = View.GONE
                    showSnackBar("Oops Something went wrong")
                }
            }
        })
    }

    private fun showSnackBar(message: String) {
        Snackbar
            .make(cl_container, message, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(ContextCompat.getColor(this, R.color.colorDarkRed))
            .setAction("RETRY") {
                userViewModel.getData()
            }
            .show()
    }

    fun onUserAvatarClicked(
        userData: UserData,
        userAvatarImageView: View,
        position: Int
    ) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(Constants.EXTRA_USER_DATA, userData)
            putExtra(
                Constants.EXTRA_USER_DATA_IMAGE_TRANSITION_NAME,
                ViewCompat.getTransitionName(userAvatarImageView)
            )
        }

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            userAvatarImageView,
            ViewCompat.getTransitionName(userAvatarImageView) ?: userData.userName
        )

        startActivityForResult(intent, Constants.USER_LIST_TO_DETAILS_REQUEST, options.toBundle())
    }

    fun onAddButtonClicked(view: View) {
        if (view is FloatingActionButton) {
            showToast("Sorry,Not implemented")
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
