package cn.vividcode.compose.sample.window

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import cn.vividcode.compose.sample.GlobalState
import cn.vividcode.compose.sample.expends.to
import java.awt.Dimension
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
	
	var windowState by mutableStateOf(getWindowState(GlobalState.currentRoute.size))
	
	private fun getWindowState(size: DpSize): WindowState {
		val mouseLocation = MouseInfo.getPointerInfo().location
		val screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices
		val bounds = screenDevices.first { mouseLocation in it.defaultConfiguration.bounds }
			.defaultConfiguration.bounds
		val position = WindowPosition(
			x = (bounds.x + (bounds.width - size.width.value) / 2f).dp,
			y = (bounds.y + (bounds.height - size.height.value) / 2f).dp
		)
		return WindowState(size = size, position = position)
	}
}

@Composable
fun FrameWindowScope.WindowZoomAnimate() {
	val animationSpec = tween<Dp>(durationMillis = 300)
	val route = GlobalState.currentRoute
	val targetWidth by animateDpAsState(route.size.width, animationSpec)
	val targetHeight by animateDpAsState(route.size.height, animationSpec) {
		setWindowMinSize(route.minSize)
	}
	LaunchedEffect(targetWidth, targetHeight) {
		setWindowSize(targetWidth, targetHeight)
	}
}

/**
 * 自适用窗口大小
 */
private fun setWindowSize(
	targetWidth: Dp,
	targetHeight: Dp,
) {
	val position = WindowManager.windowState.position
	val size = WindowManager.windowState.size
	WindowManager.windowState.size = targetWidth to targetHeight
	WindowManager.windowState.position = WindowPosition(
		x = position.x - (targetWidth - size.width) / 2f,
		y = position.y - (targetHeight - size.height) / 2f
	)
}

/**
 * 设置窗口最小值
 */
private fun FrameWindowScope.setWindowMinSize(size: DpSize?) {
	this.window.minimumSize = size?.let {
		Dimension(it.width.value.toInt(), it.height.value.toInt())
	}
}