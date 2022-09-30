package com.example.posts_app.data.repository

import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.User
import com.example.posts_app.utils.ResponseStatus

interface PostRepository {
    suspend fun getPostListFromApi(): ResponseStatus<MutableList<Post>>
    suspend fun getUsersListFromApi(): ResponseStatus<List<User>>
    suspend fun getUserFromApi(id: Int): ResponseStatus<User>

    //------- Database data
    suspend fun getPostListFromDatabase(): ResponseStatus<MutableList<Post>>
    suspend fun insertPostsIntoDatabase(postList: List<Post>): ResponseStatus<Unit>
    suspend fun insertUsersIntoDataBase(userList: List<User>): ResponseStatus<Unit>
    suspend fun getUserFromDatabase(id: Int): ResponseStatus<User>
    suspend fun updateFavouriteField(id: Int, isFavourite: Boolean): ResponseStatus<Unit>
    suspend fun deletePost(id:Int): ResponseStatus<Unit>
    suspend fun updateIsReadField(id: Int, isRead: Boolean): ResponseStatus<Unit>
    suspend fun deleteAllPosts(): ResponseStatus<Unit>
}