package com.tungdvs.languageapp.chat.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tungdvs.languageapp.base.BaseViewModel
import com.tungdvs.languageapp.base.ErrorHandler
import com.tungdvs.languageapp.chat.domain.entities.Message
import com.tungdvs.languageapp.chat.domain.usecases.GetMessagesUseCase
import com.tungdvs.languageapp.chat.domain.usecases.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.scopes.ViewModelScoped

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        loadMessages()
    }

    private fun loadMessages() {
        viewModelScope.launch {
            try {
                val messages = getMessagesUseCase()
                _uiState.update { it.copy(messages = messages) }
            } catch (e: Exception) {
                errorHandler.handleError(e)
            }
        }
    }

    fun updateInputText(text: String) {
        _uiState.update { it.copy(inputText = text) }
    }

    fun sendMessage() {
        val currentState = _uiState.value
        val newMessage = Message(
            content = currentState.inputText,
            sender = "user"
        )

        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        messages = it.messages + newMessage,
                        inputText = ""
                    )
                }

                val response = sendMessageUseCase(newMessage)
                _uiState.update {
                    it.copy(messages = it.messages + response)
                }
            } catch (e: Exception) {
                errorHandler.handleError(e)
            }
        }
    }
}

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val inputText: String = ""
)
