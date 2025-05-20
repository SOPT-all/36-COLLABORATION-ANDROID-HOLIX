package org.sopt.holix.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.Gray05
import org.sopt.holix.core.designsystem.theme.Gray07
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.designsystem.theme.White
import org.sopt.holix.presentation.home.model.Thumbnail


@Composable
fun BannerCarousel(modifier: Modifier = Modifier) {
    val thumbnails = Thumbnail.entries.map { it.resId }.toPersistentList()
    val pagerState = rememberPagerState(
        pageCount = {thumbnails.size})

    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize(),
            pageSpacing = 0.dp,
            pageSize = PageSize.Fill
        ) { page ->
            Image(
                painter = painterResource(id = thumbnails[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Box(
            modifier = modifier
                .padding(top = 15.dp, end = 14.dp)
                .background(Gray07, RoundedCornerShape(20.dp))
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${thumbnails.size}",
                color = White,
                style = HolixTheme.typography.body7R13,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 7.dp, vertical = 5.dp)
            )
        }

        Row(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 19.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in thumbnails.indices) {
                Box(
                    modifier = modifier
                        .height(8.dp)
                        .width(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (i == pagerState.currentPage) Gray05 else White
                        )
                )
                Spacer(modifier = modifier.width(9.dp))
            }
        }
    }
}