package org.sopt.holix.presentation.chatting.components.hamburger

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.sopt.holix.R

@Composable
fun ChattingMemberProfile(
    imageUrl: String,
    isOwner: Boolean,
    isActive : Boolean,
    modifier: Modifier = Modifier
) {
    Box {
        if (isActive) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_active),
                contentDescription = stringResource(R.string.chat_profile_online_content_description),
                tint = Color.Unspecified
            )
        }

        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.chat_profile_image_content_description),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(37.dp)
                .clip(CircleShape)
        )


        if (isOwner) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_crown),
                contentDescription = stringResource(R.string.chat_profile_owner_content_description),
                tint = Color.Unspecified,
                modifier = modifier.align(Alignment.BottomEnd)
            )
        }
    }
}