package com.example.posts_app.data.mappers

import com.example.posts_app.data.db.entities.PostEntity
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.dto.post.PostDto

fun PostDto.toModel() =  Post(
    userId, id, title, body, isFavourite = true
)

fun PostEntity.toModel() =  Post(
    userId, id, title, body, isFavourite
)

fun Post.toEntity() = PostEntity(
    userId, id, title, body, isFavourite
)
