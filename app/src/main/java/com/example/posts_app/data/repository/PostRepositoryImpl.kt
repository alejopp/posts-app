package com.example.posts_app.data.repository

import com.example.posts_app.data.api.ApiService
import com.example.posts_app.data.api.makeNetworkCall
import com.example.posts_app.data.db.dao.PostDao
import com.example.posts_app.data.db.makeDatabaseCall
import com.example.posts_app.data.mappers.toEntity
import com.example.posts_app.data.mappers.toModel
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.User
import com.example.posts_app.utils.ResponseStatus
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: PostDao
) : PostRepository {

    //------Api data
    override suspend fun getPostListFromApi(): ResponseStatus<MutableList<Post>> = makeNetworkCall {
        val response = api.getPosts()
        response.map { it.toModel() }.toMutableList()
    }

    override suspend fun getUsersListFromApi(): ResponseStatus<List<User>> = makeNetworkCall {
        val response = api.getUsers().toList()
        response.map { userDto ->
            userDto.toModel()
        }
    }

    override suspend fun getUserFromApi(id: Int): ResponseStatus<User> = makeNetworkCall {
        val response = api.getUser(id).toModel()
        response
    }

    //------- Database data
    override suspend fun getPostListFromDatabase(): ResponseStatus<MutableList<Post>> =
        makeDatabaseCall {
            val response = dao.getPosts()
            response.map { postEntity -> postEntity.toModel() }.toMutableList()
        }

    override suspend fun insertPostsIntoDatabase(postList: List<Post>) = makeDatabaseCall {
        dao.insertPosts(postList.map { post ->
            post.toEntity()
        })
    }

    override suspend fun insertUsersIntoDataBase(userList: List<User>) = makeDatabaseCall {
        dao.insertUsers(userList.map { user -> user.toEntity() })
    }

    override suspend fun getUserFromDatabase(id: Int): ResponseStatus<User> = makeDatabaseCall {
        dao.getUser(id).toModel()
    }

    override suspend fun updateFavouriteField(id: Int, isFavourite: Boolean) = makeDatabaseCall {
        dao.updateFavouriteField(id, isFavourite)
    }

    override suspend fun deletePost(id: Int) = makeDatabaseCall {
        dao.detetePost(id)
    }

    override suspend fun updateIsReadField(id: Int, isRead: Boolean) = makeDatabaseCall {
        dao.updateIsReadField(id, isRead)
    }

    override suspend fun deleteAllPosts() = makeDatabaseCall {
        dao.deleteAllPosts()
    }
}