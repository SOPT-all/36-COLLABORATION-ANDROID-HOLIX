package org.sopt.holix.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object ClubDetail: Route

    @Serializable
    data object ClubChat: Route

    @Serializable
    data object Chatting : Route

    @Serializable
    data object ChattingDetailHamburger : Route

    @Serializable
    data object Dummy: Route

    @Serializable
    data object DummyNext: Route
}
