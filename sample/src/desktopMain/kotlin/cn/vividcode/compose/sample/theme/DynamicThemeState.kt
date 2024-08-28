package cn.vividcode.compose.sample.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import cn.vividcode.compose.sample.theme.ColorMode.*

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/27 13:32
 *
 * 文件介绍：DynamicTheme
 */
@Composable
fun DynamicTheme(
	content: @Composable () -> Unit,
) {
	DynamicThemeState.isDarkTheme = when (DynamicThemeState.currentMode) {
		Light -> false
		Dark -> true
		System -> isSystemInDarkTheme()
	}
	
	val colorScheme = if (DynamicThemeState.isDarkTheme) {
		DynamicThemeState.currentColorSchemes.darkColorScheme
	} else {
		DynamicThemeState.currentColorSchemes.lightColorScheme
	}
	MaterialTheme(
		colorScheme = animateColorScheme(colorScheme),
		content = content
	)
}

private val animationSpec = spring<Color>(stiffness = 1000f)

@Composable
private fun animateColorScheme(colorScheme: ColorScheme): ColorScheme {
	val primary by animateColorAsState(colorScheme.primary, animationSpec)
	val onPrimary by animateColorAsState(colorScheme.onPrimary, animationSpec)
	val primaryContainer by animateColorAsState(colorScheme.primaryContainer, animationSpec)
	val onPrimaryContainer by animateColorAsState(colorScheme.onPrimaryContainer, animationSpec)
	val secondary by animateColorAsState(colorScheme.secondary, animationSpec)
	val onSecondary by animateColorAsState(colorScheme.onSecondary, animationSpec)
	val secondaryContainer by animateColorAsState(colorScheme.secondaryContainer, animationSpec)
	val onSecondaryContainer by animateColorAsState(colorScheme.onSecondaryContainer, animationSpec)
	val tertiary by animateColorAsState(colorScheme.tertiary, animationSpec)
	val onTertiary by animateColorAsState(colorScheme.onTertiary, animationSpec)
	val tertiaryContainer by animateColorAsState(colorScheme.tertiaryContainer, animationSpec)
	val onTertiaryContainer by animateColorAsState(colorScheme.onTertiaryContainer, animationSpec)
	val background by animateColorAsState(colorScheme.background, animationSpec)
	val onBackground by animateColorAsState(colorScheme.onBackground, animationSpec)
	val surface by animateColorAsState(colorScheme.surface, animationSpec)
	val onSurface by animateColorAsState(colorScheme.onSurface, animationSpec)
	val surfaceVariant by animateColorAsState(colorScheme.surfaceVariant, animationSpec)
	val onSurfaceVariant by animateColorAsState(colorScheme.onSurfaceVariant, animationSpec)
	val inverseSurface by animateColorAsState(colorScheme.inverseSurface, animationSpec)
	val inverseOnSurface by animateColorAsState(colorScheme.inverseOnSurface, animationSpec)
	val error by animateColorAsState(colorScheme.error, animationSpec)
	val onError by animateColorAsState(colorScheme.onError, animationSpec)
	val errorContainer by animateColorAsState(colorScheme.errorContainer, animationSpec)
	val onErrorContainer by animateColorAsState(colorScheme.onErrorContainer, animationSpec)
	val outline by animateColorAsState(colorScheme.outline, animationSpec)
	val outlineVariant by animateColorAsState(colorScheme.outlineVariant, animationSpec)
	val scrim by animateColorAsState(colorScheme.scrim, animationSpec)
	val surfaceBright by animateColorAsState(colorScheme.surfaceBright, animationSpec)
	val surfaceContainer by animateColorAsState(colorScheme.surfaceContainer, animationSpec)
	val surfaceContainerHigh by animateColorAsState(colorScheme.surfaceContainerHigh, animationSpec)
	val surfaceContainerHighest by animateColorAsState(colorScheme.surfaceContainerHighest, animationSpec)
	val surfaceContainerLow by animateColorAsState(colorScheme.surfaceContainerLow, animationSpec)
	val surfaceContainerLowest by animateColorAsState(colorScheme.surfaceContainerLowest, animationSpec)
	val surfaceDim by animateColorAsState(colorScheme.surfaceDim, animationSpec)
	return colorScheme.copy(
		primary = primary,
		onPrimary = onPrimary,
		primaryContainer = primaryContainer,
		onPrimaryContainer = onPrimaryContainer,
		secondary = secondary,
		onSecondary = onSecondary,
		secondaryContainer = secondaryContainer,
		onSecondaryContainer = onSecondaryContainer,
		tertiary = tertiary,
		onTertiary = onTertiary,
		tertiaryContainer = tertiaryContainer,
		onTertiaryContainer = onTertiaryContainer,
		background = background,
		onBackground = onBackground,
		surface = surface,
		onSurface = onSurface,
		surfaceVariant = surfaceVariant,
		onSurfaceVariant = onSurfaceVariant,
		inverseSurface = inverseSurface,
		inverseOnSurface = inverseOnSurface,
		error = error,
		onError = onError,
		errorContainer = errorContainer,
		onErrorContainer = onErrorContainer,
		outline = outline,
		outlineVariant = outlineVariant,
		scrim = scrim,
		surfaceBright = surfaceBright,
		surfaceContainer = surfaceContainer,
		surfaceContainerHigh = surfaceContainerHigh,
		surfaceContainerHighest = surfaceContainerHighest,
		surfaceContainerLow = surfaceContainerLow,
		surfaceContainerLowest = surfaceContainerLowest,
		surfaceDim = surfaceDim
	)
}

object DynamicThemeState {
	
	var currentColorSchemes by mutableStateOf(DefaultColorSchemes)
	
	var currentMode by mutableStateOf(System)
	
	var isDarkTheme by mutableStateOf(false)
}

enum class ColorMode {
	
	Light,
	
	Dark,
	
	System
}