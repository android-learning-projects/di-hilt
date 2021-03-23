package com.learning.dihilt

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.learning.dihilt.ui.MainActivity
import com.learning.dihilt.ui.MainFragment
import com.learning.dihilt.ui.MainFragmentFactory
import com.learning.dihilt.ui.MyModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author bsoft-61 on 17/2/21.
 * */
@ExperimentalCoroutinesApi
@UninstallModules(MyModule::class)
@HiltAndroidTest
class MainTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someString: String

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun someTest() {
        assertThat(someString,containsString("TESTING"))
    }

    @Test
    fun mainActivityTest(){
        val scenario = launchActivity<MainActivity>()
    }

    @Test
    fun mainFragmentTest(){
        val scenario = launchFragmentInContainer<MainFragment>(
            factory = fragmentFactory
        )
    }
    @Module
    @InstallIn(SingletonComponent::class)
    object AppModuleTest{

        @Singleton
        @Provides
        fun provideSomeString(): String{
            return "It's some TESTING string!"
        }
    }
}