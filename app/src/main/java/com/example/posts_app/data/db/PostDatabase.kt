package com.example.posts_app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.posts_app.data.db.dao.PostDao
import com.example.posts_app.data.db.entities.PostEntity
import com.example.posts_app.data.db.entities.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1)
abstract class PostDatabase: RoomDatabase() {
    abstract fun getPostDao(): PostDao
}