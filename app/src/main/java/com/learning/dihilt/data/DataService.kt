package com.learning.dihilt.data

import androidx.lifecycle.LiveData
import com.learning.dihilt.utils.Resource
import retrofit2.Response


/**
 * @author bsoft-61 on 15/2/21.
 * */
interface DataService {
    suspend fun getName(): String
    suspend fun getAge(): Resource<Int>
}