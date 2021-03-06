package com.example.chuck.interfaces

import com.example.chuck.model.Post
import com.example.chuck.model.PostList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("search")
    suspend fun getJokes(
        @Query("query") string: String
    ) : Response<PostList>

    @GET("random")
    suspend fun getRandom() : Response<Post>

    @GET("categories")
    suspend fun getCategories() : Response<ArrayList<String>>

    @GET("random")
    suspend fun getRandomJokeByCategories(
        @Query("category") string: String
    ) : Response<Post>
}