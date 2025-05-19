package org.sopt.holix.presentation.myclub.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.designsystem.theme.MainBlue
import org.sopt.holix.core.type.MyClubTab
import org.sopt.holix.core.util.noRippleClickable

@Composable
fun MyClubTabRow(
    tabType: PersistentList<MyClubTab>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            tabType.forEachIndexed { index, type ->
                val isSelected = selectedTabIndex == index
                Text(
                    text = stringResource(type.text),
                    textAlign = TextAlign.Center,
                    color = if (isSelected) HolixTheme.colors.black else HolixTheme.colors.gray05,
                    style = HolixTheme.typography.title1B17,
                    modifier = Modifier
                        .drawBehind {
                            if (isSelected) {
                                val strokeWidth = (1.5).dp.toPx()
                                val yPosition = size.height
                                drawLine(
                                    color = MainBlue,
                                    start = Offset(0f, yPosition),
                                    end = Offset(size.width, yPosition),
                                    strokeWidth = strokeWidth
                                )
                            }
                        }
                        .padding(bottom = 4.dp)
                        .noRippleClickable {
                            onTabSelected(index)
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyClubTabRowPreview() {
    HolixAndroidTheme {
        MyClubTabRow(
            tabType = MyClubTab.entries.toPersistentList(),
            selectedTabIndex = 0,
            onTabSelected = {}
        )
    }
}

