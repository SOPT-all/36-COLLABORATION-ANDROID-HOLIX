package org.sopt.holix.presentation.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.Gray03
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.designsystem.theme.White


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    search: String,
    onSearchChange: (String) -> Unit,
    onMenuClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(painter = painterResource(id = R.drawable.ic_hambeger),
                contentDescription = "Menu",
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedTextField(
            value = search,
            onValueChange = onSearchChange,
            placeholder = {
                Text(
                    text = "무엇을 배우고 싶으신가요?",
                    color = Gray03,
                    style = HolixTheme.typography.body3R15
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_black),
                    contentDescription = "Search",
                    tint = Gray03
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 1.dp)
                .height(50.dp)
                .padding(end = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = Gray03,
                unfocusedIndicatorColor = Gray03,
            ),
            singleLine = true
        )
    }
}