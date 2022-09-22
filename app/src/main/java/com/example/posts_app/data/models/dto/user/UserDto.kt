package com.example.posts_app.data.models.dto.user

import com.example.posts_app.data.models.dto.user.Address
import com.example.posts_app.data.models.dto.user.Company

data class UserDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)