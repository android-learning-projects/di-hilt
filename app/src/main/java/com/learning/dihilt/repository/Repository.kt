package com.learning.dihilt.repository

import com.learning.dihilt.data.DataService
import com.learning.dihilt.data.DataServiceImpl
import com.learning.dihilt.model.Blog
import com.learning.dihilt.retrofit.BlogNetworkEntity
import com.learning.dihilt.retrofit.BlogRetrofit
import com.learning.dihilt.retrofit.NetworkMapper
import com.learning.dihilt.room.BlogDao
import com.learning.dihilt.room.CacheMapper
import com.learning.dihilt.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


/**
 * @author bsoft-61 on 15/2/21.
 * */
class Repository
@Inject
constructor(
    private val dataServiceImpl: DataService,
    private val retrofit: BlogRetrofit,
    private val blogDao: BlogDao,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getName() :Flow<Resource<String>> = flow {
        emit(Resource.loading())
        delay(1000)
        try {

            val value = dataServiceImpl.getName()
            emit(Resource.success(value))
        } catch (e: Exception) {
            emit(Resource.error(e.toString(), null))
        }
    }

    suspend fun getAge() = dataServiceImpl.getAge()

    /*suspend fun getBlog(): Resource<List<BlogNetworkEntity>> {

        val response = retrofit.get()

        return if (response.isSuccessful)
            Resource.success(response.body()!!)
        else
            Resource.error("failed")
    }*/
    suspend fun getBlog(): Flow<Resource<List<Blog>>> = flow {
        emit(Resource.loading())
        delay(1000)
        try {
            val networkBlogs = retrofit.getBlogs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(Resource.success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(Resource.error(e.toString(), null))
        }
    }
}