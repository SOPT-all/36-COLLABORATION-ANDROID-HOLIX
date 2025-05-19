package org.sopt.holix.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.Black
import org.sopt.holix.core.designsystem.theme.Gray01
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.presentation.home.model.Category


@Composable
fun CategoryChips() {
    val categories = listOf(
        Category(R.drawable.ic_career, "커리어"),
        Category(R.drawable.ic_tech, "재테크"),
        Category(R.drawable.ic_heart, "자기계발"),
        Category(R.drawable.ic_studying, "학업")
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)
    ) {
        categories.forEach { category ->
            CategoryChipItem(category)
        }
    }
}

@Composable
fun CategoryChipItem(category: Category) {
    Column(
        modifier = Modifier
            .size(width = 83.dp, height = 76.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Gray01),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = category.iconRes),
            contentDescription = category.label,
            modifier = Modifier
                .padding(top = 11.dp, bottom = 15.dp)
                .height(19.dp)
                .width(18.dp)
        )

        Text(
            text = category.label,
            style = HolixTheme.typography.body7R13,
            color = Black
        )
        Spacer(modifier = Modifier.height(11.dp))
    }
}