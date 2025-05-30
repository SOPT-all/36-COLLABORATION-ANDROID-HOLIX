package org.sopt.holix.presentation.chatting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.holix.core.navigation.Route
import org.sopt.holix.presentation.chatting.ChattingRoute

fun NavController.navigateToChatting(navOptions: NavOptions? = null) = navigate(Route.Chatting, navOptions)

fun NavGraphBuilder.chattingNavGraph(
    navigateUp: () -> Unit,
    navigateNext: () -> Unit, //hamburger 이동
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    composable<Route.Chatting> {
        ChattingRoute(
            paddingValues = PaddingValues(),
            navigateUp = navigateUp,
            navigateNext = navigateNext,
            snackBarHostState = snackBarHostState,
            modifier = modifier
        )
    }
}

