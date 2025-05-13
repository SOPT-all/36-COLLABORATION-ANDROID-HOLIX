package org.sopt.holix.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    modifier: Modifier = Modifier
) {
    val snackBarHostState = remember { SnackbarHostState() }

    MainScreenContent(
        navigator = navigator,
        snackBarHostState = snackBarHostState,
        modifier = modifier
    )
}

@Composable
private fun MainScreenContent(
    navigator: MainNavigator,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = Color.White),
        content = { padding ->
            HolixNavHost(
                navigator = navigator,
                paddingValues = padding,
                snackBarHostState = snackBarHostState
            )
        },
        bottomBar = {

        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    HolixAndroidTheme {
        MainScreen(
            navigator = rememberMainNavigator()
        )
    }
}