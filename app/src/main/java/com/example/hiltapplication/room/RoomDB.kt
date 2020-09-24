package com.example.hiltapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostCacheEntity::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        val DATABASE_NAME = "post_db"
    }
}