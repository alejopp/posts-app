package com.example.posts_app.ui.home

import android.widget.Toast
import androidx.core.content.contentValuesOf
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
            val response = postRepository.getPostList()
            if (response is ApiResponseStatus.Success){
                _postList.value = response.data
                _status.value = ApiResponseStatus.Success(response)
            }
            if (response is ApiResponseStatus.Error){
                _status.value = ApiResponseStatus.Error(response.messageId)
            }
        }
    }

    fun getUserList(){
        viewModelScope.launch {
            val response = postRepository.getUsersList()
            if (response is ApiResponseStatus.Success){
                _userList.value = response.data
            }
        }
    }

    fun getUser(post: Post){
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            val response = postRepository.getUser(post.userId)
            if (response is ApiResponseStatus.Success){
                _user.value = response.data
                _status.value = ApiResponseStatus.Success(response)
            }
            if (response is ApiResponseStatus.Error){
                _status.value = ApiResponseStatus.Error(response.messageId)
            }
        }
    }
}