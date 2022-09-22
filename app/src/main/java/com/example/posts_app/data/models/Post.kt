package com.example.posts_app.data.models

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val isFavourite: Boolean
)

