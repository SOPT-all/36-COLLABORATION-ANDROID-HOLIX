package org.sopt.holix.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    snackBarHostState: SnackbarHostState
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            paddingValues = paddingValues,
            snackBarHostState = snackBarHostState
        )
    }
}