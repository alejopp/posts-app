package com.example.posts_app.data.api

import com.example.posts_app.utils.POSTS
import com.example.posts_app.utils.USERS
import retrofit2.http.GET

interface ApiService {

    @GET(POSTS)
    suspend fun getPosts(): PostListResponse

    @GET(USERS)
    suspend fun getUsers():UserListResponse
}