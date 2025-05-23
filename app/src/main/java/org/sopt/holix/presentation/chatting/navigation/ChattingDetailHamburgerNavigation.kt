package org.sopt.holix.presentation.chatting.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.holix.core.navigation.Route
import org.sopt.holix.presentation.chatting.ChattingHamburgerRoute

fun NavController.navigateToChattingHamburger(navOptions: NavOptions? = null) = navigate(Route.ChattingDetailHamburger, navOptions)

fun NavGraphBuilder.chattingHamburgerNavGraph(
    navigateUp: () -> Unit,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    composable<Route.ChattingDetailHamburger> {
        ChattingHamburgerRoute(
            navigateUp = navigateUp,
            snackBarHostState = snackBarHostState,
            modifier = modifier
        )
    }
}