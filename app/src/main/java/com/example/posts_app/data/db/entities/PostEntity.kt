package com.example.posts_app.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostEntity (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean,
    @ColumnInfo(name = "is_read") val isRead: Boolean
)