package cn.vividcode.compose.sample.expends

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.FrameWindowScope
import java.awt.Dimension

/**
 * 设置全窗口内容
 */
fun FrameWindowScope.setFullWindowContent() {
	if (CurrentOS.isMacOS) {
		this.window.rootPane.putClientProperty("apple.awt.fullWindowContent", true)
	}
}

/**
 * 设置透明标题栏
 */
fun FrameWindowScope.setTransparentTitleBar() {
	if (CurrentOS.isMacOS) {
		this.window.rootPane.putClientProperty("apple.awt.transparentTitleBar", true)
	}
}

/**
 * 设置窗口最小值
 */
fun FrameWindowScope.setMinSize(size: DpSize?) {
	this.window.minimumSize = size?.let { Dimension(it.width.value.toInt(), it.height.value.toInt()) }
}