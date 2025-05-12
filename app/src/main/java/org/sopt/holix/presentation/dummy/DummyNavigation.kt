package org.sopt.holix.presentation.dummy

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.holix.core.navigation.Route

fun NavController.navigateDummy(
    navOptions: NavOptions?
) {
    navigate(Route.Dummy, navOptions)
}

fun NavGraphBuilder.dummyNavGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateNext: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    composable<Route.Dummy> {
        DummyRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp,
            navigateNext = navigateNext,
            snackBarHostState = snackBarHostState
        )
    }
}