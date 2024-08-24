package cn.vividcode.compose.window

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import cn.vividcode.compose.expends.to
import cn.vividcode.compose.sample.RouteState
import java.awt.GraphicsEnvironment
import java.awt.MouseInfo

/**
 * 项目名称：vividcode-compose
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/24 18:27
 *
 * 文件介绍：窗口管理器
 */
object WindowManager {
	
	var windowState by mutableStateOf(getWindowState(RouteState.current.size))
	
	private fun getWindowState(size: DpSize): WindowState {
		val mouseLocation = MouseInfo.getPointerInfo().location
		val screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices
		val bounds = screenDevices.first { mouseLocation in it.defaultConfiguration.bounds }
			.defaultConfiguration.bounds
		val position = WindowPosition(
			x = (bounds.x + (bounds.width - size.width.value) / 2F).dp,
			y = (bounds.y + (bounds.height - size.height.value) / 2F).dp
		)
		return WindowState(size = size, position = position)
	}
}

@Composable
fun WindowZoomAnimate() {
	val animationSpec = tween<Dp>(durationMillis = 180, easing = LinearEasing)
	val targetWidth by animateDpAsState(RouteState.current.size.width, animationSpec)
	val targetHeight by animateDpAsState(RouteState.current.size.height, animationSpec)
	LaunchedEffect(targetWidth, targetHeight) {
		setWindowSize(targetWidth, targetHeight)
	}
}

/**
 * 自适用窗口大小
 */
fun setWindowSize(
	targetWidth: Dp,
	targetHeight: Dp
) {
	WindowManager.windowState.position = WindowManager.windowState.position.let {
		WindowPosition(
			x = it.x - (targetWidth - WindowManager.windowState.size.width) / 2F,
			y = it.y - (targetHeight - WindowManager.windowState.size.height) / 2F,
		)
	}
	WindowManager.windowState.size = targetWidth to targetHeight
}