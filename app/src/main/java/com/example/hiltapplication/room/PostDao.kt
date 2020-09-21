package com.example.hiltapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: PostCacheEntity): Long

    @Query("SELECT * FROM POSTS")
    suspend fun select(): List<PostCacheEntity>

}