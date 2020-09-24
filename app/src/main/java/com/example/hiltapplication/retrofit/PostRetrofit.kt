package com.example.hiltapplication.retrofit

import retrofit2.http.GET

interface PostRetrofit {
    @GET("posts")
    suspend fun get(): List<PostNetworkEntity>
}