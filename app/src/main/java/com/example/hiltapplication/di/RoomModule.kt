package com.example.hiltapplication.di

import android.content.Context
import androidx.room.Room
import com.example.hiltapplication.room.PostDao
import com.example.hiltapplication.room.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun providePostDB(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(
            context,
            RoomDB::class.java,
            RoomDB.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDAO(db: RoomDB): PostDao {
        return db.postDao()
    }
}