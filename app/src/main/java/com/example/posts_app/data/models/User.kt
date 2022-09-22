package com.example.posts_app.data.models

import com.example.posts_app.data.models.dto.user.Address
import com.example.posts_app.data.models.dto.user.Company

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)