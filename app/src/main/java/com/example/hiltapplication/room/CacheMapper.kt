package com.example.hiltapplication.room

import com.example.hiltapplication.model.Post
import com.example.hiltapplication.util.EntityMapper

class CacheMapper : EntityMapper<PostCacheEntity, Post> {
    override fun mapFromEntity(entity: PostCacheEntity): Post {
        return Post(
            userId = entity.userId,
            postId = entity.postId,
            postTitle = entity.postTitle,
            postBody = entity.postBody
        )
    }

    override fun mapToEntity(domainModel: Post): PostCacheEntity {
        return PostCacheEntity(
            userId = domainModel.userId,
            postId = domainModel.postId,
            postTitle = domainModel.postTitle,
            postBody = domainModel.postBody
        )
    }

    fun mapFromEntityList(entities: List<PostCacheEntity>): List<Post> {
        return entities.map { mapFromEntity(it) }
    }
}