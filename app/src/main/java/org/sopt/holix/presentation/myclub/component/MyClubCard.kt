package org.sopt.holix.presentation.myclub.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.util.noRippleClickable

@Composable
fun MyClubCard(
    clubTitle: String,
    participants: String,
    clubImage: String,
    isPinned: Boolean,
    onClick: () -> Unit,
    onPinButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lineHeight = HolixTheme.typography.body4B13.lineHeight

    val minHeightDp = with(LocalDensity.current) {
        lineHeight.toDp() * 2
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = HolixTheme.colors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = HolixTheme.colors.gray02,
                shape = RoundedCornerShape(10.dp)
            )
            .noRippleClickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ){
            AsyncImage(
                model = clubImage,
                modifier = Modifier
                    .background(
                        color = HolixTheme.colors.white,
                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                    )
                    .aspectRatio(160f / 99f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.my_club_recommend_image)
            )

            MyClubPinButton(
                isPinned = isPinned,
                onClick = onPinButtonClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }

        Spacer(Modifier.height(9.dp))

        Text(
            text = clubTitle,
            style = HolixTheme.typography.body4B13,
            color = HolixTheme.colors.black,
            modifier = Modifier
                .heightIn(min = minHeightDp)
                .padding(horizontal = 6.dp)
        )

        Spacer(Modifier.height(5.dp))

        Text(
            text = participants,
            style = HolixTheme.typography.label3R11,
            color = HolixTheme.colors.gray05,
            modifier = Modifier
                .padding(horizontal = 6.dp)
        )

        Spacer(Modifier.height(5.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun MyClubCardPreview() {
    HolixAndroidTheme {
        Row(
           horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MyClubCard(
                clubTitle = "AI 도구 활용한 영상 만들기 질문답변방",
                participants = "멤버 998명 / 1,000명",
                clubImage = "https://picsum.photos/160/99",
                isPinned = true,
                onClick = {},
                onPinButtonClick = {},
                modifier = Modifier
                    .weight(1f)
            )

            MyClubCard(
                clubTitle = "UX Writing 실무 파헤치기",
                participants = "멤버 975명 / 1,000명",
                clubImage = "",
                isPinned = false,
                onClick = {},
                onPinButtonClick = {},
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}