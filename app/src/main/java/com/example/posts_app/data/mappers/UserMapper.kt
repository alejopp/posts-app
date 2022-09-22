package com.example.posts_app.data.mappers

import com.example.posts_app.data.models.User
import com.example.posts_app.data.models.dto.user.UserDto

fun UserDto.toModel() =  User(
    address, company, email, id, name, phone, username, website
)