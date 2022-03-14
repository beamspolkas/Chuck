package com.example.chuck.api

import com.example.chuck.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts/{id}")
    suspend fun getPost(
        @Path("id") number: Int
    ) : Response<Post>

    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>

}