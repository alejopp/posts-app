package com.example.posts_app.data.repository

import com.example.posts_app.data.di.PostProvider
import com.example.posts_app.data.models.dto.PostDto

class PostRepository {
    val api = PostProvider.provideMovieApi()

    suspend fun getPostList(): List<PostDto> {
        return api.getPosts()
    }

    //TODO("MakeApiCall class")
}