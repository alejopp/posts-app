package com.example.posts_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.dto.PostDto
import com.example.posts_app.data.repository.PostRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val postRepository = PostRepository()

    private val _postList = MutableLiveData<List<PostDto>>()
    val postList: LiveData<List<PostDto>> get() = _postList


    fun getPostList(){
        viewModelScope.launch {
            val response = postRepository.getPostList()
            _postList.value = response
        }
    }
}