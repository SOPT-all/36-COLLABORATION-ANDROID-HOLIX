package org.sopt.holix.presentation.myclub.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.type.MyClubStatus
import org.sopt.holix.core.util.noRippleClickable

@Composable
fun MyClubStatusChip(
    myClubStatus: MyClubStatus,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = HolixTheme.typography.label2M11,
    onClick: () -> Unit = {},
) {
    Text(
        modifier = modifier
            .background(color = myClubStatus.backgroundColor, shape = RoundedCornerShape(size = 3.dp))
            .noRippleClickable {
                onClick()
            }
            .padding(horizontal = 4.dp, vertical = 2.dp),
        text = stringResource(myClubStatus.text),
        style = textStyle,
        color = myClubStatus.textColor,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun MyClubStatusChipPreview() {
    HolixAndroidTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 4.dp)
        ) {
            MyClubStatusChip(
                myClubStatus = MyClubStatus.RECRUITING,
            )

            MyClubStatusChip(
               myClubStatus = MyClubStatus.CLOSED
            )
        }
    }
}