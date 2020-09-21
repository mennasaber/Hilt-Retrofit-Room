package com.example.hiltapplication.room

import androidx.room.Database

@Database(entities = [PostCacheEntity::class], version = 1)
abstract class RoomDatabase {
    abstract fun postDao(): PostDao

    companion object {
        val DATABASE_NAME = "post_db"
    }
}