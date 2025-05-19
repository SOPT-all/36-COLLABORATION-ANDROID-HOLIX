package org.sopt.holix.presentation.chatting.components.detail.hamburger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.domain.model.chatting.ChattingListDataEntity

@Composable
fun ChattingHamburgerMenu(
    imageVector: ImageVector,
    contentDescription: String,
    menuName: String,
    memberList: PersistentList<ChattingListDataEntity>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(HolixTheme.colors.white)
    ) {
        Column {
            Row (
                modifier = modifier
                    .padding(vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    modifier = modifier
                        .padding(start = 11.dp),
                    tint = Color.Unspecified
                )

                Text(
                    text = menuName,
                    modifier = modifier
                        .weight(1f)
                        .padding(start = 7.dp),
                    style = HolixTheme.typography.body2M15,
                    color = HolixTheme.colors.black
                )

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right_gray),
                    contentDescription = stringResource(R.string.chat_hamburger_menu_next_content_description),
                    modifier = modifier
                        .padding(end = 11.dp)
                )
            }


            if (menuName == stringResource(R.string.chat_hamburger_menu_image)) {
                Spacer(modifier = modifier.height(11.dp))

                ChattingPhotoLazyRow()

                Spacer(modifier = modifier.height(11.dp))
            }

            if (menuName == stringResource(R.string.chat_hamburger_menu_member)) {
                ChattingMemberLazyColumn(
                    memberList = memberList
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ChattingHamburgerMenuPreview() {
    HolixAndroidTheme {
        ChattingHamburgerMenu(
            imageVector = ImageVector.vectorResource(R.drawable.ic_chatting_menu_5),
            contentDescription = stringResource(R.string.chat_hamburger_menu_vote),
            menuName = stringResource(R.string.chat_hamburger_menu_image),
            memberList = persistentListOf()
        )
    }
}