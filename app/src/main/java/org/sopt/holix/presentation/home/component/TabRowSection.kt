package org.sopt.holix.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sopt.holix.core.designsystem.theme.Black
import org.sopt.holix.core.designsystem.theme.Blue
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.designsystem.theme.White
import org.sopt.holix.presentation.home.model.TabList


@Composable
fun TabRowSection(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabItems = TabList.values()

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp,
        contentColor = Black,
        containerColor = Color.White,
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
        tabItems.forEachIndexed { index, tab ->
            Tab(
                modifier = Modifier
                    .width(40.dp),
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = tab.tabName,
                        style = if (selectedTab == index) HolixTheme.typography.body1Sb15 else HolixTheme.typography.body3R15,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            )
        }
    }
}