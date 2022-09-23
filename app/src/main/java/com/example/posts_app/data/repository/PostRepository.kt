package com.example.posts_app.data.repository

import com.example.posts_app.PostsApp
import com.example.posts_app.utils.ResponseStatus
import com.example.posts_app.data.api.makeNetworkCall
import com.example.posts_app.data.db.makeDatabaseCall
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

    //------Api data
    suspend fun getPostListFromApi(): ResponseStatus<List<Post>> = makeNetworkCall {
        val response = api.getPosts().toList()
        response.map { postDto -> postDto.toModel() }
    }

    suspend fun getUsersListFromApi(): ResponseStatus<List<User>> = makeNetworkCall {
        val response = api.getUsers().toList()
        response.map { userDto ->
            userDto.toModel()
        }
    }

    suspend fun getUserFromApi(id: Int): ResponseStatus<User> = makeNetworkCall {
        val response = api.getUser(id).toModel()
        response
    }

    //------- Database data
    suspend fun getPostListFromDatabase(): ResponseStatus<List<Post>> = makeDatabaseCall {
        val response = dao.getPosts()
        response.map { postEntity -> postEntity.toModel() }
    }

    suspend fun insertPostsIntoDatabase(postList: List<Post>) = makeDatabaseCall {
        dao.insertPosts(postList.map { post ->
            post.toEntity()
        })
    }

    suspend fun getUsersFromDatabase(): ResponseStatus<List<User>> = makeDatabaseCall {
        dao.getUsers().map { userEntity -> userEntity.toModel() }
    }

    suspend fun insertUsersIntoDataBase(userList: List<User>) = makeDatabaseCall {
        dao.insertUsers(userList.map { user -> user.toEntity() })
    }

    suspend fun getUserFromDatabase(id: Int): ResponseStatus<User> = makeDatabaseCall {
        dao.getUser(id).toModel()
    }
}