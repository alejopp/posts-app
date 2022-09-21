package com.example.posts_app.data.api

import com.example.posts_app.utils.POSTS
import retrofit2.http.GET

interface ApiService {

    @GET(POSTS)
    suspend fun getPosts(): PostListResponse
}