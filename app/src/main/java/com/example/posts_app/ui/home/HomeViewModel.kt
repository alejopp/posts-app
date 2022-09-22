package com.example.posts_app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts_app.data.api.responses.ApiResponseStatus
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.User
import com.example.posts_app.data.models.dto.user.UserDto
import com.example.posts_app.data.repository.PostRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val postRepository = PostRepository()

    private val _postList = MutableLiveData<List<Post>?>()
    val postList: LiveData<List<Post>?> get() = _postList

    private val _userList = MutableLiveData<List<User>?>()
    val userList: LiveData<List<User>?> get() = _userList

    private val _user = MutableLiveData<UserDto?>()
    val user: LiveData<UserDto?> get() = _user

    private val _status = MutableLiveData<ApiResponseStatus<Any>?>()
    val status: LiveData<ApiResponseStatus<Any>?> get() = _status

    fun getPostList(){
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            val response = postRepository.getPostListFromApi()
            if (response is ApiResponseStatus.Success){
                _postList.value = response.data
                _status.value = ApiResponseStatus.Success(response)
                insertPostsIntoDatabase(response.data)
            }
            if (response is ApiResponseStatus.Error){
                _status.value = ApiResponseStatus.Error(response.messageId)
            }
        }
    }

    fun getUserList(){
        viewModelScope.launch {
            val response = postRepository.getUsersListFromApi()
            if (response is ApiResponseStatus.Success){
                _userList.value = response.data
            }
        }
    }

    fun getUser(post: Post){
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            val response = postRepository.getUserFromApi(post.userId)
            if (response is ApiResponseStatus.Success){
                _user.value = response.data
                _status.value = ApiResponseStatus.Success(response)
            }
            if (response is ApiResponseStatus.Error){
                _status.value = ApiResponseStatus.Error(response.messageId)
            }
        }
    }

    private fun insertPostsIntoDatabase(postList: List<Post>){
        val a = postList
        viewModelScope.launch {
            try {

                postRepository.insertPostsIntoDatabase(postList)
            }catch (e: Exception){
                Log.i("Database error", "${e.message}")
            }
        }
    }
}