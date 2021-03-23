package com.learning.dihilt.ui

import androidx.lifecycle.*
import com.learning.dihilt.model.Blog
import com.learning.dihilt.repository.Repository
import com.learning.dihilt.retrofit.BlogNetworkEntity
import com.learning.dihilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author bsoft-61 on 15/2/21.
 * */
@HiltViewModel
class DataViewModel
@Inject
constructor(
    private val repository: Repository,

    ) : ViewModel() {

    var userAge = MutableLiveData<Resource<Int>>()

    private val _dataStateName: MutableLiveData<Resource<String>> = MutableLiveData()

    val dataStateName: LiveData<Resource<String>>
        get() = _dataStateName


    fun setStateEventName(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogsEvent -> {
                    repository.getName()
                        .onEach {dataState ->
                            _dataStateName.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }

    fun getAge(): MutableLiveData<Resource<Int>> {
        viewModelScope.launch {
            userAge.postValue(repository.getAge())
        }
        return userAge
    }

    private val _dataState: MutableLiveData<Resource<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<Resource<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogsEvent -> {
                    repository.getBlog()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }
}
sealed class MainStateEvent{

    object GetBlogsEvent: MainStateEvent()

    object None: MainStateEvent()
}