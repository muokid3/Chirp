package com.berxley.auth.presentation.register_success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn


class RegisterSuccessViewModel : ViewModel() {

    private val _channel = Channel<RegisterSuccessEvent>()
    val channel = _channel.receiveAsFlow()

    private var hasLoadedInitialData = false
    private val _state = MutableStateFlow(RegisterSuccessState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RegisterSuccessState()
        )

    fun onAction(action: RegisterSuccessAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }



}