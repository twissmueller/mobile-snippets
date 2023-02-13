package com.example.scaffold

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FloatingActionButton(scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    androidx.compose.material.FloatingActionButton(
        onClick = {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar("FAB Action Button Clicked")
            }
        }
    ) {
        Text("FAB")
    }
}