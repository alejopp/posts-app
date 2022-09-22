package com.example.posts_app.data.mappers

import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.dto.PostDto

fun PostDto.toModel() =  Post(
    userId, id, title, body, isFavourite = true
)