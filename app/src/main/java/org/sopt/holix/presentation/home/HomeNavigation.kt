package org.sopt.holix.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.holix.core.navigation.MainTabRoute


fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    snackBarHostState: SnackbarHostState
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            paddingValues = paddingValues,
            snackBarHostState = snackBarHostState
        )
    }
}