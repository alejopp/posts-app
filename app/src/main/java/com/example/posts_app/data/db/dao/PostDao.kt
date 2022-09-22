package com.example.posts_app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.posts_app.data.db.entities.PostEntity
import com.example.posts_app.data.db.entities.UserEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table")
    suspend fun getPosts(): List<PostEntity>

    @Query("SELECT * FROM post_table")
    suspend fun getUser(id: Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post: List<PostEntity>)
}