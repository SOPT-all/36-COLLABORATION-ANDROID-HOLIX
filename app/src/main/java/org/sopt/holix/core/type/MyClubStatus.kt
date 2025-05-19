package org.sopt.holix.core.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.AlertRed
import org.sopt.holix.core.designsystem.theme.LightBlue
import org.sopt.holix.core.designsystem.theme.LightRed
import org.sopt.holix.core.designsystem.theme.MainBlue

enum class MyClubStatus(
    @StringRes val text: Int,
    val textColor: Color,
    val backgroundColor: Color
) {
    RECRUITING(
        text = R.string.my_club_status_recruiting,
        textColor = MainBlue,
        backgroundColor = LightBlue
    ),
    CLOSED(
        text = R.string.my_club_status_closed,
        textColor = AlertRed,
        backgroundColor = LightRed
    )
}