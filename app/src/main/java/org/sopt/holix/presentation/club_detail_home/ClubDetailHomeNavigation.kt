package org.sopt.holix.presentation.club_detail_home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.holix.core.navigation.Route

fun NavController.navigateClubDetail(
    navOptions: NavOptions? = null
    ) {
    navigate(Route.ClubDetail, navOptions)
}
fun NavGraphBuilder.clubDetailNavGraph(
    paddingValues: PaddingValues,
    snackBarHostState: SnackbarHostState,
    navigateUp: () -> Unit
) {
    composable<Route.ClubDetail> {
        ClubDetailHomeRoute(
            paddingValues = paddingValues,
            snackBarHostState = snackBarHostState,
            navigateUp = navigateUp
        )
    }
}