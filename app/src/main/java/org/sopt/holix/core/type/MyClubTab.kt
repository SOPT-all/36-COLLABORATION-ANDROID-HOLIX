package org.sopt.holix.core.type

import androidx.annotation.StringRes
import org.sopt.holix.R

enum class MyClubTab(
    @StringRes val text: Int
) {
    MYCLASS(R.string.my_club),
    CLASS(R.string.my_club_class),
    MENTORING(R.string.my_club_mentoring)

}