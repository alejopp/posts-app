package com.example.posts_app.data.db

import androidx.room.Database
import com.example.posts_app.data.db.dao.PostDao
import com.example.posts_app.data.db.entities.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase {
    abstract fun getPostDao(): PostDao
}