package cn.vividcode.compose.sample

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cn.vividcode.compose.sample.Route.HomePage
import cn.vividcode.compose.sample.expends.CurrentOS
import cn.vividcode.compose.sample.home.HomePage
import cn.vividcode.compose.sample.theme.DynamicTheme
import cn.vividcode.compose.sample.theme.DynamicThemeState
import cn.vividcode.compose.sample.window.WindowManager
import cn.vividcode.compose.sample.window.WindowZoomAnimate
import cn.vividcode.compose.sample.window.compositionLocalProvider
import java.awt.Dimension

fun main() = application {
	DynamicTheme {
		Window(
			onCloseRequest = ::exitApplication,
			state = WindowManager.windowState,
			title = "",
			resizable = GlobalState.currentRoute.resizable
		) {
			InitWindow()
			WindowZoomAnimate()
			compositionLocalProvider {
				Page()
			}
		}
	}
}

@Composable
private fun FrameWindowScope.InitWindow() {
	val minSize = GlobalState.currentRoute.minSize
	LaunchedEffect(Unit) {
		if (CurrentOS.isMacOS) {
			window.rootPane.apply {
				this.putClientProperty("apple.awt.fullWindowContent", true)
				this.putClientProperty("apple.awt.transparentTitleBar", true)
			}
		}
		window.minimumSize = Dimension(minSize?.width?.value?.toInt() ?: 0, minSize?.height?.value?.toInt() ?: 0)
	}
	
	val background = MaterialTheme.colorScheme.background
	LaunchedEffect(DynamicThemeState.triggerChangeBackgroundColor) {
		window.background = java.awt.Color(background.toArgb())
	}
}


@Composable
private fun Page() {
	when (GlobalState.currentRoute) {
		HomePage -> HomePage()
	}
}