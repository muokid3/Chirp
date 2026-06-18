package com.berxley.auth.presentation.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asStateFlow


class RegisterViewModel : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _channel = Channel<RegisterEvent>()
    val channel = _channel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            else -> Unit
        }
    }

}