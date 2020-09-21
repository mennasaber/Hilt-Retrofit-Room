package com.example.hiltapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostCacheEntity(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @PrimaryKey
    @ColumnInfo(name = "post_id")
    val postId: Int,
    @ColumnInfo(name = "post_title")
    val postTitle: String,
    @ColumnInfo(name = "post_body")
    val postBody: String
)