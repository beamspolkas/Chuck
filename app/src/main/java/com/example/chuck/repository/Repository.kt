package com.example.chuck.repository

import com.example.chuck.api.RetrofitInstance
import com.example.chuck.model.Post
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPosts() : List<Response<Post>> {
        return RetrofitInstance.api.getPosts()
    }
}