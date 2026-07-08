package com.berxley.auth.presentation.email_verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asStateFlow


class EmailVerificationViewModel : ViewModel() {

    private val _state = MutableStateFlow(EmailVerificationState())
    val state = _state.asStateFlow()

    private val _channel = Channel<EmailVerificationEvent>()
    val channel = _channel.receiveAsFlow()

    fun onAction(action: EmailVerificationAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}