package com.example.posts_app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.posts_app.data.db.entities.PostEntity
import com.example.posts_app.data.db.entities.UserEntity

@Dao
interface PostDao {
    //-----Post queries
    @Query("SELECT * FROM post_table")
    suspend fun getPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post: List<PostEntity>)

    @Query("UPDATE post_table SET is_favourite = :isFavourite WHERE id = :id")
    suspend fun updateFavouriteField(id: Int, isFavourite: Boolean)

    //-------User queries
    @Query("SELECT * FROM user_table WHERE id == :id")
    suspend fun getUser(id: Int): UserEntity

    @Query("SELECT * FROM user_table")
    suspend fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(userList: List<UserEntity>)

}