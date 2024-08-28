package cn.vividcode.compose.sample.window

import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import cn.vividcode.compose.sample.GlobalState
import cn.vividcode.compose.sample.expends.toDpSize
import cn.vividcode.compose.sample.expends.toSize
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
	val density = LocalDensity.current
	val targetSize by animateSizeAsState(
		targetValue = GlobalState.currentRoute.size.toSize(),
		finishedListener = { setWindowMinSize(it, density) }
	)
	LaunchedEffect(targetSize) {
		setWindowSize(targetSize, density)
	}
}

/**
 * 自适用窗口大小
 */
private fun setWindowSize(
	targetSize: Size,
	density: Density,
) {
	val position = WindowManager.windowState.position
	val size = WindowManager.windowState.size
	val targetDpSize = targetSize.toDpSize(density)
	WindowManager.windowState.size = targetDpSize
	WindowManager.windowState.position = WindowPosition(
		x = position.x - (targetDpSize.width - size.width) / 2f,
		y = position.y - (targetDpSize.height - size.height) / 2f
	)
}

/**
 * 设置窗口最小值
 */
private fun FrameWindowScope.setWindowMinSize(size: Size?, density: Density) {
	this.window.minimumSize = size?.toDpSize(density)?.let {
		Dimension(it.width.value.toInt(), it.height.value.toInt())
	}
}