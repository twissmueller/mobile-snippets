package com.example.gptclient

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ContentView(contentViewModel: ContentViewModel = viewModel()) {

    var question by remember { mutableStateOf("") }

    Column(Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(end = 10.dp),
                value = question,
                onValueChange = { question = it }
            )
            Button(
                modifier = Modifier
                    .width(70.dp)
                    .height(56.dp),
                onClick = {
                    contentViewModel.sendRequest(question = question)
                },
                enabled = !contentViewModel.isLoading
            ) {
                Text(text = "Send")
            }
        }
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            text = contentViewModel.answer,
        )
    }
}

@Preview
@Composable
fun ContentPreview() {
    ContentView()
}