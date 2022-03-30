package com.example.chuck.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuck.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<PostList>> = MutableLiveData()
    val myPostResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myStringResponse: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun getJokes(string: String){
        viewModelScope.launch {
            val response: Response<PostList> = repository.getJokes(string)
            myResponse.value = response
        }
    }

    fun getRandom(){
        viewModelScope.launch {
            val response: Response<Post> = repository.getRandom()
            myPostResponse.value = response
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            val response: Response<ArrayList<String>> = repository.getCategories()
            myStringResponse.value = response.body()
        }
    }

    fun getRandomJokeByCategories(string: String){
        viewModelScope.launch {
            val response: Response<Post> = repository.getRandomJokeByCategories(string)
            myPostResponse.value = response
        }
    }
}
