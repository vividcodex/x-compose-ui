package cn.vividcode.compose.sample

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cn.vividcode.compose.sample.Route.GuidePage
import cn.vividcode.compose.sample.Route.HomePage
import cn.vividcode.compose.sample.expends.CurrentOS
import cn.vividcode.compose.sample.guide.GuidePage
import cn.vividcode.compose.sample.home.HomePage
import cn.vividcode.compose.sample.theme.DynamicTheme
import cn.vividcode.compose.sample.theme.SystemInDarkThemeChangeListener
import cn.vividcode.compose.sample.window.WindowManager
import cn.vividcode.compose.sample.window.WindowZoomAnimate

fun main() = application {
	DynamicTheme {
		Window(
			onCloseRequest = ::exitApplication,
			state = WindowManager.windowState,
			title = "",
			resizable = GlobalState.currentRoute.resizable
		) {
			this.window.defaultCloseOperation
			val background = MaterialTheme.colorScheme.background
			LaunchedEffect(Unit) {
				initWindow(background)
			}
			SystemInDarkThemeChangeListener()
			WindowZoomAnimate()
			Page()
		}
	}
}

private fun FrameWindowScope.initWindow(
	background: Color,
) {
	if (CurrentOS.isMacOS) {
		this.window.rootPane.apply {
			this.putClientProperty("apple.awt.fullWindowContent", true)
			this.putClientProperty("apple.awt.transparentTitleBar", true)
			this.putClientProperty("apple.awt.customMenuBar", true)
		}
	}
	this.window.background = java.awt.Color(background.toArgb())
}

@Composable
private fun Page() {
	when (GlobalState.currentRoute) {
		GuidePage -> GuidePage()
		HomePage -> HomePage()
	}
}