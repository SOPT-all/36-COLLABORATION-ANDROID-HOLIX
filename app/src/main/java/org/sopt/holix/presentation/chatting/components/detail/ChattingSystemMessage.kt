package org.sopt.holix.presentation.chatting.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme

@Composable
fun ChattingSystemMessage(
    message : String
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(HolixTheme.colors.gray03),
    ) {
        Text(
            text = message,
            style = HolixTheme.typography.label3R11,
            color = HolixTheme.colors.gray06,
            modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 3.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChattingSystemMessagePreview() {
    HolixAndroidTheme {
        ChattingSystemMessage(message = "누구님이 입장했습니다")
    }
}