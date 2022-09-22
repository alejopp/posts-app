package com.example.posts_app.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.posts_app.data.models.dto.user.Address
import com.example.posts_app.data.models.dto.user.Company


@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)