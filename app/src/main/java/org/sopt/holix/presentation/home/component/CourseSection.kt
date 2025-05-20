package org.sopt.holix.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.sopt.holix.core.designsystem.theme.Black
import org.sopt.holix.core.designsystem.theme.Gray03
import org.sopt.holix.core.designsystem.theme.Gray04
import org.sopt.holix.core.designsystem.theme.HolixTheme

import org.sopt.holix.presentation.home.model.StudyUiModel


@Composable
fun CourseSection(
    modifier: Modifier = Modifier,
    title: String,
    studies: List<StudyUiModel>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = HolixTheme.typography.title1B17,
                color = Black
            )
            Text(
                modifier = modifier
                    .padding(end = 6.dp),
                text = "더보기",
                style = HolixTheme.typography.body2M15,
                color = Gray04
            )
        }

        LazyRow(
            horizontalArrangement = spacedBy(15.dp)
        ) {
            items(studies) { study ->
                CourseCardItem(study)
            }
        }
    }
}


@Composable
fun CourseCardItem(
    study: StudyUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(256.dp)
            .height(228.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = study.imageUrl),
            contentDescription = study.studyTitle,
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))

        )
        Spacer(modifier = modifier.height(12.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            study.tags.forEach { tag ->
                TagChip(modifier = Modifier, tag)
            }
        }
        Spacer(modifier = modifier.height(12.dp))

        Text(
            text = study.studyTitle,
            style = HolixTheme.typography.title3B15,
            color = Black,
            maxLines = 1
        )
        Spacer(modifier = modifier.height(2.dp))

        Text(
            text = study.studyLeader,
            style = HolixTheme.typography.body6M13,
            color = Gray03,
            maxLines = 1
        )
        Spacer(modifier = modifier.height(2.dp))

        Text(
            text = study.price,
            style = HolixTheme.typography.body4B13,
            color = Black
        )
    }
}