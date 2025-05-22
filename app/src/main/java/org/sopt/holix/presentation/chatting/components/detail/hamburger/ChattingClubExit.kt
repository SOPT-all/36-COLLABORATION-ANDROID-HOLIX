package org.sopt.holix.presentation.chatting.components.detail.hamburger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme

@Composable
fun ChattingClubExit(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(HolixTheme.colors.white)
    ) {
        Text(
            text = stringResource(R.string.chat_club_exit),
            style = HolixTheme.typography.body2M15,
            color = HolixTheme.colors.alertRed,
            modifier = modifier
                .padding(vertical = 13.dp, horizontal = 11.dp)
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ChattingClubExitPreview() {
    HolixAndroidTheme {
        ChattingClubExit()
    }
}