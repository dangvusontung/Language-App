package com.tungdvs.languageapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tungdvs.languageapp.data.model.api.chatcompletion.Message
import com.tungdvs.languageapp.domain.usecase.SendMessageUseCase

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ChatMessages(
            messages = uiState.messages,
            modifier = Modifier.weight(1f)
        )
        ChatInput(
            value = uiState.inputText,
            onValueChange = viewModel::updateInputText,
            onSendClick = viewModel::sendMessage,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ChatMessages(
    messages: List<Message>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(messages.reversed()) { message ->
            ChatMessageItem(message)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ChatMessageItem(message: Message) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 4.dp
    ) {
        Text(
            text = message.content,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Type a message") }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onSendClick) {
            Text("Send")
        }
    }
}

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun updateInputText(text: String) {
        _uiState.update { it.copy(inputText = text) }
    }

    fun sendMessage() {
        val currentState = _uiState.value
        val newMessage = Message(role = "user", content = currentState.inputText)

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    messages = it.messages + newMessage,
                    inputText = ""
                )
            }

            val result = sendMessageUseCase.sendMessage("gpt-3.5-turbo", _uiState.value.messages)
            // Handle the result here, e.g., add the AI's response to the messages
        }
    }
}

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val inputText: String = ""
)
