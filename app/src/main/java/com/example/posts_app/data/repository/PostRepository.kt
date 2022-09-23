package com.example.posts_app.data.repository

import com.example.posts_app.PostsApp
import com.example.posts_app.data.api.responses.ApiResponseStatus
import com.example.posts_app.data.api.makeNetworkCall
import com.example.posts_app.data.db.entities.PostEntity
import com.example.posts_app.data.di.RetrofitProvider
import com.example.posts_app.data.mappers.toEntity
import com.example.posts_app.data.mappers.toModel
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.User
import com.example.posts_app.data.models.dto.user.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {
    val api = RetrofitProvider.provideRetrofitService()
    val dao = PostsApp.database.getPostDao()

    suspend fun getPostListFromApi(): ApiResponseStatus<List<Post>> = makeNetworkCall {
        val response = api.getPosts().toList()
        response.map { postDto -> postDto.toModel() }
    }

    suspend fun getUsersListFromApi(): ApiResponseStatus<List<User>> = makeNetworkCall {
        val response = api.getUsers().toList()
        response.map { userDto ->
            userDto.toModel()
        }
    }

    suspend fun getUserFromApi(id: Int): ApiResponseStatus<UserDto> = makeNetworkCall {
        val response = api.getUser(id)
        response
    }

    suspend fun getPostListFromDatabase(): List<Post> =
        withContext(Dispatchers.IO){
            val response = dao.getPosts()
            response.map { postEntity -> postEntity.toModel()
        }
    }

    suspend fun insertPostsIntoDatabase(postList: List<Post>) =
        withContext(Dispatchers.IO){
            dao.insertPosts(postList.map { post ->
                post.toEntity()
            })
        }
}