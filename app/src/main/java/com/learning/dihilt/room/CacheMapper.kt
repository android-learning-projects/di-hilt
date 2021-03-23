package com.learning.dihilt.room

import com.learning.dihilt.model.Blog
import com.learning.dihilt.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() :
    EntityMapper<BlogCacheEntity, Blog> {

    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            userId = entity.userId,
            id = entity.id,
            title = entity.title,
            isCompleted = entity.isCompleted
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            userId = domainModel.userId,
            id = domainModel.id,
            title = domainModel.title,
            isCompleted = domainModel.isCompleted
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog> {
        return entities.map { mapFromEntity(it) }
    }
}











