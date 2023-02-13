package com.example.scaffold

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ScaffoldView() {
    val scaffoldState = rememberScaffoldState()

    val topBarState = remember { mutableStateOf(true) }
    val bottomBarState = remember { mutableStateOf(true) }
    val fabState = remember { mutableStateOf(true) }

    Scaffold(
        scaffoldState = scaffoldState,
        content = { padding ->
            Content(
                padding,
                topBarState,
                bottomBarState,
                fabState
            )
        },
        topBar = { TopBar(scaffoldState, topBarState) },
        bottomBar = { BottomBar(bottomBarState) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (fabState.value) {
                FloatingActionButton(scaffoldState)
            }
        },
        drawerContent = { DrawerContent() },
    )
}