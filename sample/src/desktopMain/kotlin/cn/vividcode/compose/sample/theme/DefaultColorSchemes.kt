package cn.vividcode.compose.sample.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import cn.vividcode.compose.ui.theme.XColorSchemes

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/27 13:35
 *
 * 文件介绍：DefaultColorScheme
 */
object DefaultColorSchemes : XColorSchemes {
	
	override val lightColorScheme = lightColorScheme(
		primary = Color(0xFF4D5C92),
		onPrimary = Color(0xFFFFFFFF),
		primaryContainer = Color(0xFFDCE1FF),
		onPrimaryContainer = Color(0xFF04174B),
		inversePrimary = Color(0xFFB6C4FF),
		secondary = Color(0xFF3C6090),
		onSecondary = Color(0xFFFFFFFF),
		secondaryContainer = Color(0xFFD4E3FF),
		onSecondaryContainer = Color(0xFF001C3A),
		tertiary = Color(0xFF515B92),
		onTertiary = Color(0xFFFFFFFF),
		tertiaryContainer = Color(0xFFDEE0FF),
		onTertiaryContainer = Color(0xFF0A154B),
		background = Color(0xFFFAF8FF),
		onBackground = Color(0xFF1A1B21),
		surface = Color(0xFFF9F9FF),
		onSurface = Color(0xFF1A1B20),
		surfaceVariant = Color(0xFFE1E2EC),
		onSurfaceVariant = Color(0xFF44474F),
		inverseSurface = Color(0xFF2F3036),
		inverseOnSurface = Color(0xFFF0F0F7),
		error = Color(0xFFBA1A1A),
		onError = Color(0xFFFFFFFF),
		errorContainer = Color(0xFFFFDAD6),
		onErrorContainer = Color(0xFF410002),
		outline = Color(0xFF75777F),
		outlineVariant = Color(0xFFC4C6D0),
		scrim = Color(0xFF000000),
		surfaceBright = Color(0xFFF9F9FF),
		surfaceContainer = Color(0xFFEEEDF4),
		surfaceContainerHigh = Color(0xFFE8E7EE),
		surfaceContainerHighest = Color(0xFFE2E2E9),
		surfaceContainerLow = Color(0xFFF3F3FA),
		surfaceContainerLowest = Color(0xFFFFFFFF),
		surfaceDim = Color(0xFFD9D9E0),
	)
	
	override val darkColorScheme = darkColorScheme(
		primary = Color(0xFFB6C4FF),
		onPrimary = Color(0xFF1D2D61),
		primaryContainer = Color(0xFF354479),
		onPrimaryContainer = Color(0xFFDCE1FF),
		inversePrimary = Color(0xFF4D5C92),
		secondary = Color(0xFFA5C8FF),
		onSecondary = Color(0xFF00315E),
		secondaryContainer = Color(0xFF214876),
		onSecondaryContainer = Color(0xFFD4E3FF),
		tertiary = Color(0xFFBAC3FF),
		onTertiary = Color(0xFF222C61),
		tertiaryContainer = Color(0xFF394379),
		onTertiaryContainer = Color(0xFFDEE0FF),
		background = Color(0xFF121318),
		onBackground = Color(0xFFE3E1E9),
		surface = Color(0xFF111318),
		onSurface = Color(0xFFE2E2E9),
		surfaceVariant = Color(0xFF44474F),
		onSurfaceVariant = Color(0xFFC4C6D0),
		inverseSurface = Color(0xFFE2E2E9),
		inverseOnSurface = Color(0xFF2F3036),
		error = Color(0xFFFFB4AB),
		onError = Color(0xFF690005),
		errorContainer = Color(0xFF93000A),
		onErrorContainer = Color(0xFFFFDAD6),
		outline = Color(0xFF8E9099),
		outlineVariant = Color(0xFF44474F),
		scrim = Color(0xFF000000),
		surfaceBright = Color(0xFF37393E),
		surfaceContainer = Color(0xFF1E1F25),
		surfaceContainerHigh = Color(0xFF282A2F),
		surfaceContainerHighest = Color(0xFF33353A),
		surfaceContainerLow = Color(0xFF1A1B20),
		surfaceContainerLowest = Color(0xFF0C0E13),
		surfaceDim = Color(0xFF111318),
	)
}