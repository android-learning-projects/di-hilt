package com.learning.dihilt.di

import com.learning.dihilt.data.DataService
import com.learning.dihilt.data.DataServiceImpl
import com.learning.dihilt.repository.Repository
import com.learning.dihilt.retrofit.BlogRetrofit
import com.learning.dihilt.retrofit.NetworkMapper
import com.learning.dihilt.room.BlogDao
import com.learning.dihilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author bsoft-61 on 15/2/21.
 * */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideDataService(): DataService {
        return DataServiceImpl()
    }

    @Singleton
    @Provides
    fun provideRepository(
        dataServiceImpl: DataService,
        retrofit: BlogRetrofit,
        blogDao: BlogDao,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): Repository {
        return Repository(dataServiceImpl, retrofit, blogDao, cacheMapper, networkMapper)
    }
}