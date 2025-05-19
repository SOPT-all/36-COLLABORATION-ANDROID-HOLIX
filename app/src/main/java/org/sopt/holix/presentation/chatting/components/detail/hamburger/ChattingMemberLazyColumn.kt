package org.sopt.holix.presentation.chatting.components.detail.hamburger

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
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
            ChattingMemberHeader()
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
            memberList = dummyList
        )
    }
}