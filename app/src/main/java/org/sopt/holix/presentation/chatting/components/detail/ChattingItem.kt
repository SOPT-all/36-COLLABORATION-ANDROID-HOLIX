package org.sopt.holix.presentation.chatting.components.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme

@Composable
fun ChattingItem(
    nickname : String,
    introduction : String,
    profileUrl : String,
    chat : String,
    likes : Int,
    date : String,
    isMine : Boolean,
    modifier: Modifier = Modifier,
    onEmojiClick : () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                //Todo : 더블 클릭 시 또는 롱 클릭 시 이모지되게
                onEmojiClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isMine) {
            Box {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_active),
                    contentDescription = stringResource(R.string.chat_profile_online_content_description),
                    tint = Color.Unspecified
                )

                AsyncImage(
                    model = profileUrl,
                    contentDescription = stringResource(R.string.chat_profile_image_content_description),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(37.dp)
                        .clip(CircleShape)
                )


                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_crown),
                    contentDescription = stringResource(R.string.chat_profile_owner_content_description),
                    tint = Color.Unspecified,
                    modifier = modifier.align(Alignment.BottomEnd)
                )
            }

            Spacer(modifier = modifier.width(6.dp))
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        Row {
            Column (
                modifier = modifier
                    .wrapContentHeight()
            ) {
                if (!isMine) {
                    //닉네임 + 한마디
                    ChattingItemTop(
                        nickname = nickname,
                        introduction = introduction,
                        modifier = modifier.padding(bottom = 3.dp)
                    )

                    //말풍선 & 칩 & 날짜
                    ChattingItemBubbleAndBottom(
                        chat = chat,
                        likes = likes,
                        date = date,
                        modifier = modifier
                    )
                } else {
                    //말풍선 & 칩 & 날짜
                    ChattingMyBubbleAndChip(
                        chat = chat,
                        likes = likes,
                        date = date,
                        modifier = modifier
                    )
                }
            }
        }

        if (isMine) {
            Spacer(modifier = modifier.width(6.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChattingItemPreview() {
    HolixAndroidTheme {
        ChattingItem(
            nickname = "김솝트",
            chat = "같이 들으실분!",
            profileUrl = "",
            likes = 3,
            introduction = "배워서 어쩌구 저쩌구구구구구",
            date = "2025-03-05T21:30:00",
            isMine = false,
            onEmojiClick = {}
        )
    }
}
