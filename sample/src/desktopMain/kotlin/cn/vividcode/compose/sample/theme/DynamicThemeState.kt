package cn.vividcode.compose.sample.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cn.vividcode.compose.sample.theme.ColorMode.*
import java.awt.Toolkit
import java.beans.PropertyChangeListener

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
	val colorScheme = when (DynamicThemeState.currentMode) {
		Light -> DynamicThemeState.currentColorSchemes.lightColorScheme
		Dark -> DynamicThemeState.currentColorSchemes.darkColorScheme
		System -> DynamicThemeState.currentColorSchemes.let {
			if (isSystemInDarkTheme()) it.darkColorScheme else it.lightColorScheme
		}
	}
	MaterialTheme(
		colorScheme = colorScheme,
		content = content
	)
}

@Composable
fun SystemInDarkThemeChangeListener() {
	DisposableEffect(Unit) {
		val name = "SystemInDarkThemeChangeListener"
		val listener = PropertyChangeListener {
			println(it.propertyName)
		}
		val toolkit = Toolkit.getDefaultToolkit()
		toolkit.addPropertyChangeListener(name, listener)
		
		onDispose {
			println("??")
//			toolkit.removePropertyChangeListener(name, listener)
		}
	}
}

object DynamicThemeState {
	
	var currentColorSchemes by mutableStateOf(DefaultColorSchemes)
	
	var currentMode by mutableStateOf(System)
}

enum class ColorMode {
	
	Light,
	
	Dark,
	
	System
}