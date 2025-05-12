package org.sopt.holix.presentation.dummy.next

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DummyNextRoute(
    paddingValues: PaddingValues
) {
    DummyNextScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun DummyNextScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Text(
        text = "dummy next",

        modifier = modifier
            .padding(paddingValues)
    )
}