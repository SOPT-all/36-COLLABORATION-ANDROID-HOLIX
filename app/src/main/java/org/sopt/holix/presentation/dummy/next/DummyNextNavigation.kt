package org.sopt.holix.presentation.dummy.next

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.holix.core.navigation.Route

fun NavController.navigateDummyNext(
    navOptions: NavOptions?
) {
    navigate(Route.DummyNext, navOptions)
}

fun NavGraphBuilder.dummyNextNavGraph(
    paddingValues: PaddingValues,
) {
    composable<Route.DummyNext> {
        DummyNextRoute(
            paddingValues = paddingValues,
        )
    }
}