package org.sopt.holix.presentation.chatting.components.detail.hamburger

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.holix.R

@Composable
fun ChattingHamburgerTopBar(
    //뒤로 가기
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            modifier = modifier
                .padding(vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left_black),
                contentDescription = stringResource(R.string.back_content_description),
                modifier = modifier
                    .padding(start = 10.dp, end = 9.dp, top = 7.dp, bottom = 7.dp)
                    .clickable {
                        navigateUp()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChattingHamburgerTopBarPreview() {
    HolixAndroidTheme {
        ChattingHamburgerTopBar(
            navigateUp = {}
        )
    }
}