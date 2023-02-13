package com.example.scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun Content(
    padding: PaddingValues,
    topState: MutableState<Boolean>,
    bottomState: MutableState<Boolean>,
    fabState: MutableState<Boolean>
) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ) {
        Text(text = "Top Start", modifier = Modifier.align(Alignment.TopStart))
        Text(text = "Top End", modifier = Modifier.align(Alignment.TopEnd))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        topState.value = !topState.value
                    }
                }) {
                Text("Top Bar")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        fabState.value = !fabState.value
                    }
                }) {
                Text("Floating Action Button")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        bottomState.value = !bottomState.value
                    }
                }) {
                Text("Bottom Bar")
            }
        }
        Text(text = "Bottom Start", modifier = Modifier.align(Alignment.BottomStart))
        Text(text = "Bottom End", modifier = Modifier.align(Alignment.BottomEnd))
    }
}