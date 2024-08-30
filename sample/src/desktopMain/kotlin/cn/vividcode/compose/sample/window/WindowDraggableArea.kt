package cn.vividcode.compose.sample.window

import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/31 01:11
 *
 * 文件介绍：WindowDraggableArea
 */
@Composable
fun WindowDraggableArea(
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit = {},
) {
	LocalFrameWindowScope.current?.WindowDraggableArea(
		modifier = modifier,
		content = content,
	)
}

@Composable
fun FrameWindowScope.compositionLocalProvider(
	content: @Composable () -> Unit,
) {
	CompositionLocalProvider(
		value = LocalFrameWindowScope provides this,
		content = content,
	)
}

private val LocalFrameWindowScope = staticCompositionLocalOf<FrameWindowScope?> { null }