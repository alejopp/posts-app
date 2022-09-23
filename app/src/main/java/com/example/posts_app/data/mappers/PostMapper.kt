package com.example.posts_app.data.mappers

import com.example.posts_app.data.db.entities.PostEntity
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.dto.post.PostDto

fun PostDto.toModel() =  Post(
    userId = userId,
    id = id,
    title = title,
    body = body,
    isFavourite = false,
    isRead = id > 20
)

fun PostEntity.toModel() =  Post(
    userId = userId,
    id = id,
    title = title,
    body = body,
    isFavourite = isFavourite,
    isRead = isRead
)

fun Post.toEntity() = PostEntity(
    userId = userId,
    id = id,
    title = title,
    body = body,
    isFavourite = isFavourite,
    isRead = isRead
)
