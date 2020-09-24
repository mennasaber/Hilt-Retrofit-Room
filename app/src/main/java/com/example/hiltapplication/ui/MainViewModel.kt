package com.example.hiltapplication.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.hiltapplication.model.Post
import com.example.hiltapplication.repository.MainRepository
import com.example.hiltapplication.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Post>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Post>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetPostsEvent -> {

                    mainRepository.getPost().onEach { dataState -> _dataState.value = dataState }
                        .launchIn(viewModelScope)


                }
                is MainStateEvent.None -> {
                }
            }
        }
    }

}


sealed class MainStateEvent {
    object GetPostsEvent : MainStateEvent()
    object None : MainStateEvent()
}