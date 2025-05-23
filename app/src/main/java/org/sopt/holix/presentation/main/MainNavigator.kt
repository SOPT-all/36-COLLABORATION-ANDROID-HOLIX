package org.sopt.holix.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import org.sopt.holix.core.navigation.MainTabRoute
import org.sopt.holix.core.navigation.Route
import org.sopt.holix.presentation.dummy.navigateDummy
import org.sopt.holix.presentation.dummy.next.navigateDummyNext
import org.sopt.holix.presentation.myclub.navigateMyClub

class MainNavigator(
    val navController: NavHostController
) {
    // 화면 보기 위해서 시작 위치 MyClub 임의설정
    val startDestination = Route.ClubDetail

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToDummy(navOptions: NavOptions? = null) {
        navController.navigateDummy(navOptions = navOptions)
    }

    fun navigateDummyNext(navOptions: NavOptions? = null) {
        navController.navigateDummyNext(navOptions = navOptions)
    }

    fun navigateToMyClub(navOptions: NavOptions? = null) {
        navController.navigateMyClub(navOptions = navOptions)
    }

    fun navigateClubDetailHome(clubId: Long) {
        // navController.navigateClubDetailHome(clubId)
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}