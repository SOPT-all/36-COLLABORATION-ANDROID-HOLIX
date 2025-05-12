package org.sopt.holix.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import org.sopt.holix.presentation.dummy.dummyNavGraph
import org.sopt.holix.presentation.dummy.next.dummyNextNavGraph

@Composable
fun HolixNavHost(
    navigator: MainNavigator,
    paddingValues: PaddingValues,
    snackBarHostState: SnackbarHostState
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        dummyNavGraph(
            paddingValues = paddingValues,
            navigateUp = navigator::navigateUp,
            navigateNext = navigator::navigateDummyNext,
            snackBarHostState = snackBarHostState
        )

        dummyNextNavGraph(
            paddingValues = paddingValues
        )
    }
}