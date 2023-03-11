package com.example.impulsetesttask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val mutableIsLoading = MutableStateFlow(true)
    val isLoading = mutableIsLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1500)
            mutableIsLoading.value = false
        }
    }
}