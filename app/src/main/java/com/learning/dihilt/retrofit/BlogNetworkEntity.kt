package com.learning.dihilt.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author bsoft-61 on 15/2/21.
 * */
data class BlogNetworkEntity(
    @SerializedName("userId")
    @Expose
    var userId: Int,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("completed")
    @Expose
    var isCompleted: Boolean
)