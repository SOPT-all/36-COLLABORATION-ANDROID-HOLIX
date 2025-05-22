package org.sopt.holix.presentation.club_detail_home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.holix.R

enum class ClubMenuItem(
    @StringRes val labelRes: Int,
    val label: String,
    @DrawableRes val iconRes: Int
) {
    MEETING(R.string.description_meeting, "모임", R.drawable.ic_club_category_4),
    MENTORING(R.string.description_mentoring, "멘토링", R.drawable.ic_club_category_1),
    CLASS(R.string.description_class, "클래스", R.drawable.ic_club_category_2),
    QUIZ(R.string.description_quiz, "퀴즈", R.drawable.ic_club_category_3);

    companion object {
        val items get() = entries
    }
}