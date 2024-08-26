package cn.vividcode.compose.sample

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/25 20:40
 *
 * 文件介绍：GlobalState
 */
object GlobalState {
	
	var currentRoute by mutableStateOf(Route.GuidePage)
	
	val snackbarHostState = SnackbarHostState()
}

/**
 * 路由
 */
fun routeTo(route: Route) {
	GlobalState.currentRoute = route
}

/**
 * 展示提示框
 */
suspend fun showSnackbar(
	message: String,
	actionLabel: String? = null,
	withDismissAction: Boolean = false,
	duration: SnackbarDuration = if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
) {
	GlobalState.snackbarHostState.showSnackbar(
		message = message,
		actionLabel = actionLabel,
		withDismissAction = withDismissAction,
		duration = duration
	)
}