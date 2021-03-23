package com.learning.dihilt.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


/**
 * @author bsoft-61 on 17/2/21.
 * */
@ExperimentalCoroutinesApi
class MainFragmentFactory
@Inject
constructor(
): FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MainFragment::class.java.name->{
                val fragment = MainFragment()
                fragment
            }
            else->super.instantiate(classLoader, className)
        }
    }
}