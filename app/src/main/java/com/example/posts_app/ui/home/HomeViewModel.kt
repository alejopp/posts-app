package com.example.posts_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts_app.data.api.ApiResponseStatus
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.dto.PostDto
import com.example.posts_app.data.repository.PostRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val postRepository = PostRepository()

    private val _postList = MutableLiveData<List<Post>?>()
    val postList: LiveData<List<Post>?> get() = _postList

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
}