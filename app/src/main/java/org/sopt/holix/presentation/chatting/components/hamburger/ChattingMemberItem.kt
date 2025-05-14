package org.sopt.holix.presentation.chatting.components.hamburger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme

@Composable
fun ChattingMemberItem(
    //Description : primitive 타입으로 받기, 혹시 몰라 우선 다 추가해놓음
    chattingId: Long,
    userName: String,
    introduction: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        when (chattingId) {
            //Description : 여러 상황 보여주기 위해 이런 식으로
            1L -> {
                ChattingMemberProfile(
                    imageUrl = imageUrl,
                    isOwner = true,
                    isActive = false,
                    modifier = modifier
                )
            }
            4L -> {
                ChattingMemberProfile(
                    imageUrl = imageUrl,
                    isOwner = true,
                    isActive = true,
                    modifier = modifier
                )
            }
            else -> {
                ChattingMemberProfile(
                    imageUrl = imageUrl,
                    isOwner = false,
                    isActive = true,
                    modifier = modifier
                )
            }
        }

        Spacer(modifier = modifier.padding(10.dp))

        Column (
            modifier = modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = userName,
                style = HolixTheme.typography.body3R15,
                color = HolixTheme.colors.black
            )

            if (introduction.isNotEmpty()) {
                Text(
                    text = introduction,
                    overflow = TextOverflow.Ellipsis,
                    style = HolixTheme.typography.body7R13,
                    color = HolixTheme.colors.gray05
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ChattingMemberItemPreview() {
    HolixAndroidTheme {
        ChattingMemberItem(
            chattingId = 4,
            userName = "손민성",
            introduction = "안드 고수가 될꺼야",
            imageUrl = "",
        )
    }
}