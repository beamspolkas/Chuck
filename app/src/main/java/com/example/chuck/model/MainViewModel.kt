package com.example.chuck.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuck.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response: Response<Post> = repository.getPost()
            myResponse.value = response
        }
    }

    val myResponses: MutableLiveData<List<Response<Post>>> = MutableLiveData()

    fun getPosts(){
        viewModelScope.launch {
            val responses: List<Response<Post>> = repository.getPosts()
            myResponses.value = responses//?????
        }
    }

}
