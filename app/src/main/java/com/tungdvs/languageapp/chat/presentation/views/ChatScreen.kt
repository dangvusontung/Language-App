package com.tungdvs.languageapp.chat.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tungdvs.languageapp.chat.domain.entities.Message
import com.tungdvs.languageapp.chat.presentation.viewmodels.ChatViewModel

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
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = message.sender,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
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
