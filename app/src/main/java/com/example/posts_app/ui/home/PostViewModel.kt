package com.example.posts_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts_app.utils.ResponseStatus
import com.example.posts_app.data.models.Post
import com.example.posts_app.data.models.User
import com.example.posts_app.data.repository.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepositoryImpl) : ViewModel() {

    private val _postList = MutableLiveData<MutableList<Post>?>()
    val postList: LiveData<MutableList<Post>?> get() = _postList

    private val _favouritePostList = MutableLiveData<MutableList<Post>?>()
    val favouritePostList: LiveData<MutableList<Post>?> get() = _favouritePostList

    private val _userList = MutableLiveData<List<User>?>()
    val userList: LiveData<List<User>?> get() = _userList

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _status = MutableLiveData<ResponseStatus<Any>?>()
    val status: LiveData<ResponseStatus<Any>?> get() = _status

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean> get() = _isFavourite

    private val _isRead = MutableLiveData<Boolean>()
    val isRead: LiveData<Boolean> get() = _isRead

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

    private fun getUserListFromApi(){
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

    fun updateFavouriteField(post: Post){
        viewModelScope.launch {
            postRepository.updateFavouriteField(post.id, !post.isFavourite)
            _isFavourite.value = !post.isFavourite
        }
    }

    fun updateIsReadField(post: Post){
        viewModelScope.launch {
            postRepository.updateIsReadField(post.id, !post.isRead)
            _isRead.value = !post.isRead
        }
    }

    fun deletePost(id: Int){
        viewModelScope.launch {
            postRepository.deletePost(id)
            println("Post deleted")
        }
    }

    fun getFavouritePosts() {
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            val favouritePostListTemp = postRepository.getPostListFromDatabase()
            if (favouritePostListTemp is ResponseStatus.Success){
                _favouritePostList.value = favouritePostListTemp.data.filter { post ->
                    post.isFavourite
                }.toMutableList()
                _status.value = ResponseStatus.Success(favouritePostListTemp)
            }
            if(favouritePostListTemp is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(favouritePostListTemp.messageId)
            }
        }
    }

    fun deleteAllPosts() {
        viewModelScope.launch {
            _status.value = ResponseStatus.Loading()
            val response = postRepository.deleteAllPosts()
            if (response is ResponseStatus.Success){
                _postList.value = mutableListOf()
                _status.value = ResponseStatus.Success(response.data)
            }
            if (response is ResponseStatus.Error){
                _status.value = ResponseStatus.Error(response.messageId)
            }
        }
    }
}