package org.sopt.holix.presentation.chatting.components.detail.hamburger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.component.StableImage
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme

@Composable
fun ChattingPhotoLazyRow(
    modifier: Modifier = Modifier
) {
    val photoItem = listOf(
        R.drawable.img_chat_photo_1,
        R.drawable.img_chat_photo_2,
        R.drawable.img_chat_photo_3,
        R.drawable.img_chat_photo_4,
    )

    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(photoItem.size) {
            StableImage(
                drawableResId = photoItem[it],
                contentDescription = stringResource(R.string.chat_hamburger_menu_image_content_description),
                modifier = modifier
                    .size(55.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChattingPhotoLazyRowPreview() {
    HolixAndroidTheme {
        ChattingPhotoLazyRow()
    }
}