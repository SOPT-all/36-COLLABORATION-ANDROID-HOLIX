package org.sopt.holix.presentation.club_detail_home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CLUB_DETAIL_HOME_ROUTE = "clubDetailHome"

fun NavGraphBuilder.clubDetailHomeScreen(
    snackBarHostState: SnackbarHostState,
    navigateUp: () -> Unit,
    navigateNext: () -> Unit
) {
    composable(route = CLUB_DETAIL_HOME_ROUTE) {
        ClubDetailHomeRoute(
            paddingValues = androidx.compose.foundation.layout.PaddingValues(),
            snackBarHostState = snackBarHostState,
            navigateUp = navigateUp,
            navigateNext = navigateNext
        )
    }
}