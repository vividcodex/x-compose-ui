package cn.vividcode.compose.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import cn.vividcode.compose.sample.expends.to

/**
 * 项目名称：vividcode-compose
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/24 22:04
 *
 * 文件介绍：Route
 */
enum class Route(
	val size: DpSize,
	val resizable: Boolean,
	val minSize: DpSize? = null
) {
	
	AuthPage(
		size = 600.dp to 450.dp,
		resizable = false
	),
	
	HomePage(
		size = 900.dp to 700.dp,
		resizable = true,
		minSize = 500.dp to 350.dp
	)
}

object RouteState {
	
	var current by mutableStateOf(Route.AuthPage)
}