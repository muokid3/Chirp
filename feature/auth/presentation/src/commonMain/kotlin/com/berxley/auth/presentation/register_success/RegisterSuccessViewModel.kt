package com.berxley.auth.presentation.register_success

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berxley.core.domain.auth.AuthService
import com.berxley.core.domain.util.onFailure
import com.berxley.core.domain.util.onSuccess
import com.berxley.core.presentation.util.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RegisterSuccessViewModel(
    private val authService: AuthService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _channel = Channel<RegisterSuccessEvent>()
    val channel = _channel.receiveAsFlow()

    private var hasLoadedInitialData = false
    val email = savedStateHandle.get<String>("email")
        ?: throw IllegalStateException("No email passed to register success screen")
    private val _state = MutableStateFlow(
        RegisterSuccessState(
            registeredEmail = email
        )
    )
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
            RegisterSuccessAction.OnLoginClick -> resendVerification()
            RegisterSuccessAction.OnResendVerificationEmailClick -> TODO()
        }
    }

    private fun resendVerification() {
        if (_state.value.isResendingVerificationEmail) {
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isResendingVerificationEmail = true) }

            authService.resendVerificationEmail((email))
                .onSuccess {
                    _state.update {
                        it.copy(
                            isResendingVerificationEmail = false,
                        )
                    }
                    _channel.send(RegisterSuccessEvent.ResendVerificationEmailSuccess)

                }
                .onFailure { error ->

                    _state.update {
                        it
                            .copy(
                                isResendingVerificationEmail = false,
                                resendVerificationError = error.toUiText()
                            )
                    }

                }

        }


    }


}