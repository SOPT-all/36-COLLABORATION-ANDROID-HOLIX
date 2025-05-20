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
import kotlinx.collections.immutable.toPersistentList
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.Black
import org.sopt.holix.core.designsystem.theme.Gray01
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.presentation.home.model.Category


@Composable
fun CategoryChips(modifier: Modifier = Modifier) {
    val categories = Category.entries.toPersistentList()

    Row(
        modifier = modifier
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
fun CategoryChipItem(category: Category, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
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