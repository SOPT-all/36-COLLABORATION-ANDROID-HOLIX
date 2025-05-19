package org.sopt.holix.presentation.chatting.components.detail.hamburger

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity
import org.sopt.holix.domain.model.chatting.ChattingType
import java.time.LocalDateTime

@Composable
fun ChattingMemberLazyColumn(
    memberList: PersistentList<ChattingListDataEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
            .height((49 + 53 * memberList.size + 4 * memberList.size).dp)
    ) {
        item {
            Row (
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_plus_large),
                    contentDescription = stringResource(R.string.chat_add_circle_content_description),
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .background(HolixTheme.colors.gray01, shape = CircleShape)
                        .clip(CircleShape)
                        .border(1.dp, HolixTheme.colors.gray02, CircleShape)
                        .padding(11.dp)
                )

                Spacer(modifier = modifier.width(14.dp))

                Text(
                    text = stringResource(R.string.chat_member_header_text),
                    style = HolixTheme.typography.body1Sb15,
                    color = HolixTheme.colors.mainBlue,
                    textAlign = TextAlign.Center
                )
            }
        }

        items(memberList.size) { memberIndex ->
            ChattingMemberItem(
                chattingId = memberList[memberIndex].chattingId,
                userName = memberList[memberIndex].userName,
                introduction = memberList[memberIndex].introduction,
                imageUrl = memberList[memberIndex].imageUrl,
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ChattingMemberLazyColumnPreview() {
    val dummyList = persistentListOf(
        ChattingListDataEntity(
            chattingId = 1,
            clubId = 1,
            userName = "송혜음",
            introduction = "저는 송혜음입니다.",
            imageUrl = "",
            contents = "이건 테스트 메시지입니다.",
            likes = 3,
            chattingType = ChattingType.USER,
            isMine = false,
            createdAt = LocalDateTime.now()
        ),

        ChattingListDataEntity(
            chattingId = 2,
            clubId = 1,
            userName = "조운재",
            introduction = "저는 조운재.",
            imageUrl = "",
            contents = "반갑습니다",
            likes = 3,
            chattingType = ChattingType.USER,
            isMine = true,
            createdAt = LocalDateTime.now()
        ),

        ChattingListDataEntity(
            chattingId = 3,
            clubId = 1,
            userName = "김솝트",
            introduction = "저는 김솝트.",
            imageUrl = "",
            contents = "김솝트님이 입장했습니다.",
            likes = 0,
            chattingType = ChattingType.SYSTEM,
            isMine = false,
            createdAt = LocalDateTime.now()
        ),

        ChattingListDataEntity(
            chattingId = 4,
            clubId = 1,
            userName = "전윤혁",
            introduction = "저는 전윤혁.",
            imageUrl = "",
            contents = "반갑습니다",
            likes = 3,
            chattingType = ChattingType.USER,
            isMine = true,
            createdAt = LocalDateTime.now()
        )
    )
    HolixAndroidTheme {
        ChattingMemberLazyColumn(
            dummyList
        )
    }
}