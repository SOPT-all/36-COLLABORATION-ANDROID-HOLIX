package org.sopt.holix.presentation.home.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sopt.holix.core.designsystem.theme.Black
import org.sopt.holix.core.designsystem.theme.Blue
import org.sopt.holix.core.designsystem.theme.HolixTheme


@Composable
fun TabRowSection(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val categories = listOf("추천", "강의", "스터디", "북클럽", "멘토링", "커뮤니티")

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp,
        contentColor = Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .height(2.dp),
                color = Blue
            )
        },
        modifier = modifier
            .height(46.dp)
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                modifier = modifier
                    .width(40.dp),
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = category,
                        style = if (selectedTab == index) HolixTheme.typography.body1Sb15 else HolixTheme.typography.body3R15,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            )
        }
    }
}