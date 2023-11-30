package com.example.expense_tracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.R

// Set of Material typography styles to start with
object AppFont {
    val FontFamily = FontFamily(
        Font(R.font.helvetica),
        Font(R.font.helvetica_bold,FontWeight.Bold),
        Font(R.font.helvetica_light,FontWeight.Light),
        Font(R.font.helvetica_normal,FontWeight.Normal)

    )
}

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.FontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.FontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.FontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.FontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.FontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.FontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.FontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.FontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.FontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.FontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.FontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.FontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.FontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.FontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.FontFamily)
)