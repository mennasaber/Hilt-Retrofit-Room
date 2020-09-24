package com.example.hiltapplication.repository

import com.example.hiltapplication.model.Post
import com.example.hiltapplication.retrofit.NetworkMapper
import com.example.hiltapplication.retrofit.PostRetrofit
import com.example.hiltapplication.room.CacheMapper
import com.example.hiltapplication.room.PostDao
import com.example.hiltapplication.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MainRepository constructor(
    private val postDao: PostDao,
    private val retrofit: PostRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getPost(): Flow<DataState<List<Post>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkPosts = retrofit.get()
            val posts = networkMapper.mapFromEntityList(networkPosts)
            for (post in posts)
                postDao.insert(cacheMapper.mapToEntity(post))
            val cachePosts = postDao.select()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachePosts)))
        } catch (e: Exception) {
            val cachePosts = postDao.select()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachePosts)))
            //emit(DataState.Error(e))
        }
    }
}