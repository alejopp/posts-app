package com.example.posts_app

import android.app.Application
import androidx.room.Room
import com.example.posts_app.data.db.PostDatabase
import com.example.posts_app.utils.DATABASE_NAME

class PostsApp: Application() {
    companion object{
        lateinit var database: PostDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, PostDatabase::class.java, DATABASE_NAME).build()
    }
}