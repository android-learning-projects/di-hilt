package com.learning.dihilt.retrofit

import com.learning.dihilt.model.Blog
import com.learning.dihilt.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() :
    EntityMapper<BlogNetworkEntity, Blog> {

    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            userId = entity.userId,
            id = entity.id,
            title = entity.title,
            isCompleted = entity.isCompleted
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            userId = domainModel.userId,
            id = domainModel.id,
            title = domainModel.title,
            isCompleted = domainModel.isCompleted
        )
    }


    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog> {
        return entities.map { mapFromEntity(it) }
    }

}





















