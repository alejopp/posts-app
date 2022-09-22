package com.example.posts_app.data.repository

import com.example.posts_app.data.api.ApiResponseStatus
import com.example.posts_app.data.api.makeNetworkCall
import com.example.posts_app.data.di.PostProvider
import com.example.posts_app.data.mappers.toModel
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.User

class PostRepository {
    val api = PostProvider.provideRetrofitService()

    suspend fun getPostList(): ApiResponseStatus<List<Post>>  = makeNetworkCall {
        val response = api.getPosts().toList()
        response.map { postDto -> postDto.toModel() }
    }

    suspend fun getUsersList(): ApiResponseStatus<List<User>>  = makeNetworkCall {
        val response = api.getUsers().toList()
        response.map {  userDto ->
            userDto.toModel()
        }
    }

}