package com.example.chuck.repository

import com.example.chuck.api.RetrofitInstance
import com.example.chuck.model.Post
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPost(number: Int) : Response<Post> {
        return RetrofitInstance.api.getPost(number)
    }

    suspend fun getPosts() : Response<List<Post>> {
        return RetrofitInstance.api.getPosts()
    }
}