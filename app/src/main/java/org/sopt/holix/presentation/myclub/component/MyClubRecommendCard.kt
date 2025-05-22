package org.sopt.holix.presentation.myclub.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.type.MyClubStatus

@Composable
fun MyClubRecommendCard(
    clubTitle: String,
    clubMaster: String,
    participants: String,
    myClubStatus: MyClubStatus,
    @DrawableRes clubImage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = clubImage),
            contentDescription = stringResource(R.string.my_club_recommend_image),
            modifier = Modifier
                .size(42.dp)
        )

        Spacer(Modifier.width(11.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = clubTitle,
                style = HolixTheme.typography.body4B13,
                color = HolixTheme.colors.black
            )

            Text(
                text = clubMaster,
                style = HolixTheme.typography.body7R13,
                color = HolixTheme.colors.gray05
            )

            MyClubParticipantStatus(
                participants = participants,
                myClubStatus = myClubStatus
            )
        }
    }
}

@Composable
fun MyClubParticipantStatus(
    participants: String,
    myClubStatus: MyClubStatus,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 2.dp),
            text = participants,
            style = HolixTheme.typography.label3R11,
            color = HolixTheme.colors.gray05
        )

        Spacer(Modifier.width(4.dp))

        MyClubStatusChip(
            myClubStatus = myClubStatus
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyClubRecommendCardPreview() {
    HolixAndroidTheme {
        MyClubRecommendCard(
            clubTitle = "UI/UX 원칙&UX 분석 Case Study - 매일 업로드",
            clubMaster = "전민수",
            participants = "멤버 975명 / 1,000명",
            myClubStatus = MyClubStatus.RECRUITING,
            clubImage = R.drawable.img_my_recommmend_1_and
        )
    }
}