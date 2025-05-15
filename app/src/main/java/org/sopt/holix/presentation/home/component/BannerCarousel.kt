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
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.Gray05
import org.sopt.holix.core.designsystem.theme.Gray07
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.designsystem.theme.White


@Composable
fun BannerCarousel() {
    val thumbnails = listOf(
        R.drawable.img_home_thumnail_1,
        R.drawable.img_home_thumnail_2,
        R.drawable.img_home_thumnail_3,
        R.drawable.img_home_thumnail_4,
        R.drawable.img_home_thumnail_5,
        R.drawable.img_home_thumnail__and6,
    )
    val pagerState = rememberPagerState(
        pageCount = {thumbnails.size})

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
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
            modifier = Modifier
                .padding(top = 15.dp, end = 14.dp)
                .height(27.dp)
                .width(41.dp)
                .background(Gray07, RoundedCornerShape(20.dp))
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${thumbnails.size}",
                color = White,
                style = HolixTheme.typography.body7R13,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 19.dp ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in thumbnails.indices) {
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (i == pagerState.currentPage) Gray05 else White
                        )
                )
                Spacer(modifier = Modifier.width(9.dp))
            }
        }
    }
}