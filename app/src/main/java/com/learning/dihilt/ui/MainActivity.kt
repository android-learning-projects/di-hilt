package com.learning.dihilt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.learning.dihilt.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    /* @Inject
     lateinit var someClass: SomeClass*/

    @Inject
    lateinit var car: Car
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*  println(someClass.doAThing())
          println(someClass.doSomeOther())
          println(car.start())*/


    }
}


class SomeClass
@Inject
constructor(private var someInterfaceImpl: SomeInterface, private var gson: Gson) {
    fun doAThing() = "I did a thing!"

    fun doSomeOther() = someInterfaceImpl.getAThing()
}

class SomeOtherClass
@Inject
constructor() {
    fun doSomeOtherThing() = "I did a some other thing!"
}


class SomeInterfaceImpl
@Inject
constructor(
    private val someDependency: String,
) : SomeInterface {
    override fun getAThing(): String {
        return "A Thing, $someDependency"
    }
}

interface SomeInterface {
    fun getAThing(): String
}

@InstallIn(SingletonComponent::class)
@Module
class MyModule {

    @Singleton
    @Provides
    fun provideSomeString(): String {
        return "some string"
    }

    @Singleton
    @Provides
    fun provideSomeInterface(
        somString: String,
    ): SomeInterface {
        return SomeInterfaceImpl(somString)
    }


}

class Car
@Inject
constructor(private val engine: Engine) {
    fun start() = engine.start()
}

class Engine
@Inject
constructor() {
    fun start() = "engine started!"
}