package com.learning.dihilt.utils

import android.view.View
import android.widget.ProgressBar


/**
 * @author bsoft-61 on 16/2/21.
 * */

fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}