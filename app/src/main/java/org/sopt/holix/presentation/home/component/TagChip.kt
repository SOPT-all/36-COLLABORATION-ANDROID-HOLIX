package org.sopt.holix.presentation.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.holix.core.designsystem.theme.Blue
import org.sopt.holix.core.designsystem.theme.Gray06
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.presentation.home.model.TagUiModel


@Composable
fun TagChip(
    modifier: Modifier = Modifier,
    tag: TagUiModel) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = when (tag.color) {
                    "blue" -> Blue
                    "gray" -> Gray06
                    else -> Gray06
                },
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 7.dp, vertical = 3.dp)
    ){
        Text(
            text = tag.name,
            style = HolixTheme.typography.label2M11,
            color = when (tag.color) {
                "blue" -> Blue
                "gray" -> Gray06
                else -> Gray06
            }
        )
    }
}
