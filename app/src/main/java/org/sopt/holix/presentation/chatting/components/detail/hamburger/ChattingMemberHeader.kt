package org.sopt.holix.presentation.chatting.components.detail.hamburger

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme

@Composable
fun ChattingMemberHeader(
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_add_circle_blue),
            contentDescription = stringResource(R.string.chat_add_circle_content_description),
            tint = Color.Unspecified
        )

        Spacer(modifier = modifier.width(8.dp))

        Text(
            text = stringResource(R.string.chat_member_header_text),
            style = HolixTheme.typography.body1Sb15,
            color = HolixTheme.colors.mainBlue,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ChattingMemberHeaderPreview() {
    HolixAndroidTheme {
        ChattingMemberHeader()
    }
}