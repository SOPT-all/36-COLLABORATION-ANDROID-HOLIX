package org.sopt.holix.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

private val localHolixColors = staticCompositionLocalOf<HolixColors> {
    error("No HolixColors provided")
}

private val localHolixTypography = staticCompositionLocalOf<HolixTypography> {
    error("No HolixTypography provided")
}

object HolixTheme {
    val colors: HolixColors
        @Composable
        @ReadOnlyComposable
        get() = localHolixColors.current

    val typography: HolixTypography
        @Composable
        @ReadOnlyComposable
        get() = localHolixTypography.current
}

@Composable
fun ProvideHolixColorsAndTypography(
    colors: HolixColors,
    typography: HolixTypography,
    content: @Composable () -> Unit
) {
    val provideColors = remember { colors.copy() }
    provideColors.update(colors)

    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)

    CompositionLocalProvider(
        localHolixColors provides provideColors,
        localHolixTypography provides provideTypography,
        content = content
    )
}

@Composable
fun HolixAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = holixColors()
    val typography = HolixTypography()

    ProvideHolixColorsAndTypography(colors, typography) {
        MaterialTheme(content = content)
    }
}