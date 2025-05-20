package org.sopt.holix.presentation.home.model

import androidx.activity.compose.R

enum class Category(val iconRes: Int, val label: String) {
    CAREER(R.drawable.ic_career, "커리어"),
    FINANCE(R.drawable.ic_tech, "재테크"),
    SELF_DEVELOP(R.drawable.ic_heart, "자기계발"),
    STUDY(R.drawable.ic_studying, "학업");
}
