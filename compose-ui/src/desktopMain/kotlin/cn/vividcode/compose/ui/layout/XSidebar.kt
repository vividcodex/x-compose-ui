package cn.vividcode.compose.ui.layout

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/26 01:06
 *
 * 文件介绍：XSidebar
 */
@Composable
fun XSidebar(
	sidebarState: MutableState<Boolean> = remember { mutableStateOf(true) },
	sidebarWidth: Dp = 250.dp,
	sidebarTop: Dp = 0.dp,
	sidebar: @Composable BoxScope.() -> Unit,
	content: @Composable BoxScope.() -> Unit,
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
	) {
		Box(
			modifier = Modifier
				.width(sidebarWidth)
				.fillMaxHeight()
				.background(MaterialTheme.colorScheme.surfaceContainer)
				.padding(top = sidebarTop)
		) {
			sidebar()
		}
		val offsetX by animateDpAsState(if (sidebarState.value) sidebarWidth else Dp.Hairline)
		Row(
			modifier = Modifier.fillMaxSize(),
		) {
			Box(
				modifier = Modifier
					.weight(1f)
					.padding(start = offsetX)
					.fillMaxHeight()
					.background(MaterialTheme.colorScheme.surface)
			) {
				content()
			}
		}
		Switch(
			checked = sidebarState.value,
			onCheckedChange = {
				sidebarState.value = it
			},
			modifier = Modifier
				.align(Alignment.BottomEnd)
				.padding(start = 12.dp, end = 12.dp)
				.size(60.dp),
			colors = SwitchDefaults.colors(
				checkedThumbColor = Color.Black,
				checkedTrackColor = Color.White
			)
		)
	}
}