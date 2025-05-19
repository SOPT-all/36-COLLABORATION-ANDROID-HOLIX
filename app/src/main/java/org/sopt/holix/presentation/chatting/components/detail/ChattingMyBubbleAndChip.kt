package org.sopt.holix.presentation.chatting.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import org.sopt.holix.presentation.chatting.utils.DateUtils.toFormattedTimeString

@Composable
fun ChattingMyBubbleAndChip(
    chat: String,
    likes: Int,
    date: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(5.dp)
    ) {
        Text(
            text = chat,
            style = HolixTheme.typography.body3R15,
            color = HolixTheme.colors.white,
            modifier = modifier
                .clip(RoundedCornerShape(topStart = 11.dp, bottomStart = 11.dp, bottomEnd = 11.dp))
                .background(HolixTheme.colors.mainBlue)
                .padding(7.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 30.dp, end = 2.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = modifier.align(Alignment.Bottom),
                text = date.toFormattedTimeString(),
                style = HolixTheme.typography.label4M9,
                color = HolixTheme.colors.gray05
            )

            Spacer(modifier.width(3.dp))

            Row (
                modifier = modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(HolixTheme.colors.gray01),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (likes > 0) {
                        ImageVector.vectorResource(R.drawable.ic_like_circle_blue)
                    } else {
                        ImageVector.vectorResource(R.drawable.ic_like_circle)
                    },
                    contentDescription = stringResource(R.string.chat_emoji_icon_content_description),
                    tint = Color.Unspecified,
                    modifier = modifier
                        .padding(3.dp)
                )

                Text(
                    text = likes.toString(),
                    style = HolixTheme.typography.label3R11,
                    color = HolixTheme.colors.black,
                    modifier = modifier
                        .padding(end = 3.dp, top = 3.dp, bottom = 3.dp),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChattingMyBubblePreview() {
    HolixAndroidTheme {
        ChattingMyBubbleAndChip(
            chat = "ㅎㅇㅎㅇㅎㅇㅎ",
            likes = 1,
            date = "2024-05-03T13:02:00"
        )
    }
}