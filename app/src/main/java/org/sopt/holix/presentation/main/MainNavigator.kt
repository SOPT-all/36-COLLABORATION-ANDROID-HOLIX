package org.sopt.holix.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import org.sopt.holix.core.navigation.Route
import org.sopt.holix.presentation.dummy.navigateDummy
import org.sopt.holix.presentation.dummy.next.navigateDummyNext

class MainNavigator(
    val navController: NavHostController
) {
    val startDestination = Route.Dummy

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToDummy(navOptions: NavOptions? = null) {
        navController.navigateDummy(navOptions = navOptions)
    }

    fun navigateDummyNext(navOptions: NavOptions? = null) {
        navController.navigateDummyNext(navOptions = navOptions)
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}