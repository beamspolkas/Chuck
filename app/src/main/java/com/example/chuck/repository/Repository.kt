package com.example.chuck.repository

import com.example.chuck.api.RetrofitInstance
import com.example.chuck.model.Post
import com.example.chuck.model.PostList
import retrofit2.Response

class Repository {

    suspend fun getJokes(string: String) : Response<PostList> {
        return RetrofitInstance.api.getJokes(string)
    }

    suspend fun getRandom(): Response<Post> {
        return RetrofitInstance.api.getRandom()
    }

    suspend fun getCategories(): Response<ArrayList<String>> {
        return RetrofitInstance.api.getCategories()
    }

    suspend fun getRandomJokeByCategories(string: String) : Response<Post> {
        return RetrofitInstance.api.getRandomJokeByCategories(string)
    }
}