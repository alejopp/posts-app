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
    suspend fun getPostListFromApi(): ResponseStatus<MutableList<Post>> = makeNetworkCall {
        val response = api.getPosts()
        response.map { it.toModel() }.toMutableList()
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
    suspend fun getPostListFromDatabase(): ResponseStatus<MutableList<Post>> = makeDatabaseCall {
        val response = dao.getPosts()
        response.map { postEntity -> postEntity.toModel() }.toMutableList()
    }

    suspend fun insertPostsIntoDatabase(postList: List<Post>) = makeDatabaseCall {
        dao.insertPosts(postList.map { post ->
            post.toEntity()
        })
    }

    suspend fun insertUsersIntoDataBase(userList: List<User>) = makeDatabaseCall {
        dao.insertUsers(userList.map { user -> user.toEntity() })
    }

    suspend fun getUserFromDatabase(id: Int): ResponseStatus<User> = makeDatabaseCall {
        dao.getUser(id).toModel()
    }

    suspend fun updateFavouriteField(id: Int, isFavourite: Boolean) = makeDatabaseCall {
        dao.updateFavouriteField(id,isFavourite)
    }

    suspend fun deletePost(id:Int) = makeDatabaseCall {
        dao.detetePost(id)
    }

    suspend fun updateIsReadField(id: Int, isRead: Boolean) = makeDatabaseCall {
        dao.updateIsReadField(id, isRead)
    }
}