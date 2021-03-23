package com.learning.dihilt.data

import androidx.lifecycle.LiveData
import com.learning.dihilt.utils.Resource
import retrofit2.Response
import javax.inject.Inject


/**
 * @author bsoft-61 on 15/2/21.
 * */
class DataServiceImpl
@Inject
constructor() : DataService {
    override suspend fun getName(): String {
        return "got data!"
    }

    override suspend fun getAge(): Resource<Int> {
        return Resource.success(22)
    }
}