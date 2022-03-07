package com.example.chuck.api

import com.example.chuck.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts/1")
    suspend fun getPost() : Response<Post>
}