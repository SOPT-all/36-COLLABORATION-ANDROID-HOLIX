package org.sopt.holix.presentation.chatting.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.domain.model.chatting.ChattingScreenType

@Composable
fun ChattingTopBar(
    screenType: ChattingScreenType,
    //뒤로 가기
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(if (screenType == ChattingScreenType.Detail) {
                HolixTheme.colors.white
            } else {
                HolixTheme.colors.gray01
            }),
    ) {
        Row (
            modifier = modifier
                .padding(vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left_black),
                contentDescription = stringResource(R.string.back_content_description),
                modifier = modifier
                    .then(
                        if (screenType == ChattingScreenType.Hamburger) {
                            modifier.padding(start = 10.dp, end = 9.dp, top = 7.dp, bottom = 7.dp)
                        } else {
                            modifier.padding(start = 10.dp, end = 9.dp)
                        }
                    )
                    .clickable {
                        //navigateUp()
                    }
            )

            if (screenType == ChattingScreenType.Detail) {
                //Todo : 나중에 뷰 합칠 때 받아오기
                Text(
                    text = stringResource(R.string.chatting_club_name),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    style = HolixTheme.typography.body3R15,
                    color = HolixTheme.colors.black,
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 2.dp)
                )

                Row (
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ){
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_search_black),
                        contentDescription = stringResource(R.string.chat_search_content_description),
                        modifier = modifier
                            .padding(5.dp)
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_hambeger),
                        contentDescription = stringResource(R.string.chat_hamburger_content_description),
                        tint = HolixTheme.colors.black,
                        modifier = modifier
                            .padding(5.dp)
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChattingTopBarPreview() {
    HolixAndroidTheme {
        ChattingTopBar(
            screenType = ChattingScreenType.Detail,
            navigateUp = {}
        )
    }
}