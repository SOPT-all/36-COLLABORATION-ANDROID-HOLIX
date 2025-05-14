package org.sopt.holix.presentation.chatting.components.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme

@Composable
fun ChattingItemTop(
    nickname : String,
    introduction : String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = nickname,
            style = HolixTheme.typography.body6M13,
            color = HolixTheme.colors.black
        )

        Text(
            text = stringResource(R.string.introduction_divider),
            modifier = Modifier.padding(horizontal = 4.dp),
            style = HolixTheme.typography.label2M11,
            color = HolixTheme.colors.gray05
        )

        Text(
            text = introduction,
            style = HolixTheme.typography.label2M11,
            color = HolixTheme.colors.gray05
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChattingItemTopPreview() {
    HolixAndroidTheme {
        ChattingItemTop(
            nickname = "김솝트",
            introduction = "배워서 어쩌구 저쩌구구구구구"
        )
    }
}