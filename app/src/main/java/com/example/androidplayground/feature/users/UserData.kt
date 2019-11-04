package com.example.androidplayground.feature.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_table")
data class UserData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val userId: Int,

    @SerializedName("login")
    @ColumnInfo(name = "NAME")
    val userName: String,

    @SerializedName("type")
    @ColumnInfo(name = "TYPE")
    val type: String,

    @SerializedName("avatar_url")
    @ColumnInfo(name = "AVATAR_URL")
    val avatarUrl: String = ""
) : Serializable