package com.example.posts_app.data.models.dto.post

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)