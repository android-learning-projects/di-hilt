package com.learning.dihilt.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


/**
 * @author bsoft-61 on 15/2/21.
 * */
interface BlogRetrofit {
    @GET("todos")
    suspend fun getBlogs(): List<BlogNetworkEntity>
}