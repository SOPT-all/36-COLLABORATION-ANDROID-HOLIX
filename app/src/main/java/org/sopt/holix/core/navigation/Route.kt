package org.sopt.holix.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object ClubDetail: Route

    @Serializable
    data object ClubChat: Route
}