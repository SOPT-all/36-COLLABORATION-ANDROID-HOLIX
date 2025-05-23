package org.sopt.holix.core.navigation

import kotlinx.serialization.Serializable

sealed interface MainTabRoute: Route {

    @Serializable
    data object MyClub: MainTabRoute

    @Serializable
    data object Notification: MainTabRoute

    @Serializable
    data object Profile: MainTabRoute
}