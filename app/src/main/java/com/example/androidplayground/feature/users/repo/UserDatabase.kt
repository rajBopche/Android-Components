package com.example.androidplayground.feature.users.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidplayground.feature.users.UserData

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getDataBaseInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }


}