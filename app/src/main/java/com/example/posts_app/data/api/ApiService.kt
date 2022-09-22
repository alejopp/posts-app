package com.example.posts_app.data.api

import com.example.posts_app.data.api.responses.PostListResponse
import com.example.posts_app.data.api.responses.UserListResponse
import com.example.posts_app.data.models.dto.user.UserDto
import com.example.posts_app.utils.POSTS
import com.example.posts_app.utils.USERS
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(POSTS)
    suspend fun getPosts(): PostListResponse

    @GET(USERS)
    suspend fun getUsers(): UserListResponse

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserDto
}