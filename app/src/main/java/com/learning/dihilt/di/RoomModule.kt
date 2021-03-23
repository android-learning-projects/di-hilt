package com.learning.dihilt.di

import android.content.Context
import androidx.room.Room
import com.learning.dihilt.data.DataService
import com.learning.dihilt.data.DataServiceImpl
import com.learning.dihilt.repository.Repository
import com.learning.dihilt.retrofit.BlogRetrofit
import com.learning.dihilt.room.BlogDao
import com.learning.dihilt.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author bsoft-61 on 15/2/21.
 * */
@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }
}