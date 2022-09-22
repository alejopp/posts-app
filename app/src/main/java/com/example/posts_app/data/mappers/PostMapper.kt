package com.example.posts_app.data.mappers

import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.dto.post.PostDto

fun PostDto.toModel() =  Post(
    userId, id, title, body, isFavourite = true
)