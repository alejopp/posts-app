package com.example.posts_app.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val street: String,
    val suit: String,
    val city: String,
    val phone: String,
    val website: String,
    @ColumnInfo(name = "company_name") val company: String,
    @ColumnInfo(name = "catch_phrase") val catchPhrase: String
)