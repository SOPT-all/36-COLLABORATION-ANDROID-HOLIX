package org.sopt.holix.presentation.myclub

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.holix.core.navigation.MainTabRoute

fun NavController.navigateMyClub(
    navOptions: NavOptions? = null
) {
    navigate(MainTabRoute.MyClub, navOptions)
}

fun NavGraphBuilder.myClubNavGraph(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    navigateClubDetailHome: (Long) -> Unit,
    snackBarHostState: SnackbarHostState
) {
    composable<MainTabRoute.MyClub> {
        MyClubRoute(
            paddingValues = paddingValues,
            navigateUp = navigateUp,
            navigateClubDetailHome = navigateClubDetailHome,
            snackBarHostState = snackBarHostState
        )
    }
}