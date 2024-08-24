package cn.vividcode.compose.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cn.vividcode.compose.sample.Route.AuthPage
import cn.vividcode.compose.sample.Route.HomePage
import cn.vividcode.compose.auth.AuthPage
import cn.vividcode.compose.expends.setFullWindowContent
import cn.vividcode.compose.expends.setMinSize
import cn.vividcode.compose.expends.setTransparentTitleBar
import cn.vividcode.compose.home.HomePage
import cn.vividcode.compose.window.WindowManager
import cn.vividcode.compose.window.WindowZoomAnimate

fun main() = application {
	Window(
		onCloseRequest = ::exitApplication,
		state = WindowManager.windowState,
		title = "",
		resizable = RouteState.current.resizable
	) {
		LaunchedEffect(Unit) {
			setFullWindowContent()
			setTransparentTitleBar()
		}
		LaunchedEffect(RouteState.current) {
			setMinSize(RouteState.current.minSize)
		}
		WindowZoomAnimate()
		Main()
	}
}

@Composable
private fun Main() {
	MaterialTheme {
		Scaffold(
			modifier = Modifier.fillMaxSize()
				.background(MaterialTheme.colors.background)
		) {
			RouteConfig()
		}
	}
}

@Composable
private fun RouteConfig() {
	when (RouteState.current) {
		AuthPage -> AuthPage()
		HomePage -> HomePage()
	}
}