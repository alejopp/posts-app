package com.example.posts_app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts_app.utils.ResponseStatus
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

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _status = MutableLiveData<ResponseStatus<Any>?>()
    val status: LiveData<ResponseStatus<Any>?> get() = _status

    fun getPosts(){
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            val response = postRepository.getPostListFromDatabase()
            if (response is ResponseStatus.Success){
                if (response.data.isEmpty()){ // If db is empty we fetch data from the api
                    getPostListFromApi()
                }
                else{
                    _postList.value = response.data
                    _status.value = ResponseStatus.Success(response)
                }
            }
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Success(response.messageId)
            }
        }
    }

    private fun getPostListFromApi(){
        viewModelScope.launch {
            val response = postRepository.getPostListFromApi()
            if (response is ResponseStatus.Success){
                _postList.value = response.data
                _status.value = ResponseStatus.Success(response)
                insertPostsIntoDatabase(response.data)
                getUserListFromApi()
            }
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }

    fun getUserListFromApi(){
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            val response = postRepository.getUsersListFromApi()
            if (response is ResponseStatus.Success){
                insertUsersIntoDatabase(response.data)
                _status.value = ResponseStatus.Success(response.data)
            }
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }

    fun getUser(post: Post){
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            var response = postRepository.getUserFromDatabase(post.userId)
            println("Userid  ${post.userId}")
            if (response is ResponseStatus.Success){
                _user.value = response.data
                _status.value = ResponseStatus.Success(response)
                println("User retrieved from database")
            }
            if (response is ResponseStatus.Error){
                response = postRepository.getUserFromApi(post.userId)
                if (response is ResponseStatus.Success){
                    _user.value = response.data
                    _status.value = ResponseStatus.Success(response.data)
                    println("User retrieved from api")
                }
                if (response is ResponseStatus.Error){
                    _status.value = ResponseStatus.Error(response.messageId)
                }
            }
        }
    }

    private fun insertPostsIntoDatabase(postList: List<Post>){
        viewModelScope.launch {
            val response = postRepository.insertPostsIntoDatabase(postList)
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }

    private fun insertUsersIntoDatabase(userList: List<User>){
        viewModelScope.launch {
            val response =  postRepository.insertUsersIntoDataBase(userList)
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }

}