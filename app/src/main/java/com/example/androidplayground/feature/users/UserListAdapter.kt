package com.example.androidplayground.feature.users

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidplayground.R
import kotlinx.android.synthetic.main.layout_user_list_item.view.*


class UserListAdapter(private val context: Context, private var userList: List<UserData>) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_list_item, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bindData(position)
    }

    fun updateAdapter(modifiedUserList: List<UserData>) {
        this.userList = modifiedUserList
        notifyDataSetChanged()
    }

    inner class UserListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.tv_title)
        private val type: TextView = view.findViewById(R.id.tv_type)
        private val avatar: ImageView = view.findViewById(R.id.iv_avatar)


        fun bindData(position: Int) {
            title.text = userList[position].userName
            type.text = userList[position].type
            Glide.with(context)
                .load(userList[position].avatarUrl)
                .placeholder(R.mipmap.ic_place_holder_round)
                .thumbnail(0.2f)
                .fallback(R.mipmap.ic_place_holder_round)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.progressBar.visibility = View.GONE
                        return false
                    }

                }).into(avatar)

            ViewCompat.setTransitionName(avatar, userList[position].userName)

            avatar.setOnClickListener {
                (context as UsersActivity).onUserAvatarClicked(userList[position], it, position)
            }
        }

    }
}