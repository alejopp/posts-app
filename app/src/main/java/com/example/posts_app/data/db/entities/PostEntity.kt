package com.example.posts_app.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
    val title: String,
    val body: String,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean,
)