package org.sopt.holix.presentation.myclub.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.util.noRippleClickable

@Composable
fun MyClubPinButton(
    isPinned: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(HolixTheme.colors.background)
            .noRippleClickable {
                onClick()
            }

    ) {
        Icon(
            imageVector = if(isPinned) ImageVector.vectorResource(R.drawable.ic_pin_fillied) else ImageVector.vectorResource(R.drawable.ic_pin),
            tint = Color.Unspecified,
            contentDescription = stringResource(R.string.my_club_pin_button),
            modifier = Modifier
                .padding(6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyClubPinButtonPreview() {
    HolixAndroidTheme {
        MyClubPinButton(
            isPinned = true,
            onClick = {}
        )
    }
}