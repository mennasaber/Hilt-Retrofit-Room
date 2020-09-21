package com.example.hiltapplication.retrofit

import com.example.hiltapplication.model.Post
import com.example.hiltapplication.util.EntityMapper

class NetworkMapper : EntityMapper<PostNetworkEntity, Post> {
    override fun mapFromEntity(entity: PostNetworkEntity): Post {
        return Post(
            userId = entity.userId,
            postId = entity.postId,
            postTitle = entity.postTitle,
            postBody = entity.postBody
        )
    }

    override fun mapToEntity(domainModel: Post): PostNetworkEntity {
        return PostNetworkEntity(
            userId = domainModel.userId,
            postId = domainModel.postId,
            postTitle = domainModel.postTitle,
            postBody = domainModel.postBody
        )
    }

    fun mapFromEntityList(entities: List<PostNetworkEntity>): List<Post> {
        return entities.map { mapFromEntity(it) }
    }
}