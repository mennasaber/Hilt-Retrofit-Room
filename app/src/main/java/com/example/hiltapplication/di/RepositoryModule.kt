package com.example.hiltapplication.di

import com.example.hiltapplication.repository.MainRepository
import com.example.hiltapplication.retrofit.NetworkMapper
import com.example.hiltapplication.retrofit.PostRetrofit
import com.example.hiltapplication.room.CacheMapper
import com.example.hiltapplication.room.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        postDao: PostDao,
        retrofit: PostRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(
            postDao = postDao,
            retrofit = retrofit,
            cacheMapper = cacheMapper,
            networkMapper = networkMapper
        )
    }
}