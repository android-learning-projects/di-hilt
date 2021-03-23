package com.learning.dihilt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author bsoft-61 on 15/2/21.
 * */
data class Blog(
    var userId: Int,
    var id: Int,
    var title: String,
    var isCompleted: Boolean
)