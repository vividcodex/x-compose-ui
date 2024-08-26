package cn.vividcode.compose.sample.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowPlacement
import cn.vividcode.compose.sample.GlobalState
import cn.vividcode.compose.sample.showSnackbar
import cn.vividcode.compose.sample.window.WindowManager
import cn.vividcode.compose.ui.layout.XCollapse
import cn.vividcode.compose.ui.layout.XSidebar

/**
 * 项目名称：vividcode-compose
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/24 21:10
 *
 * 文件介绍：主页
 */
@Composable
fun HomePage() {
	Scaffold(
		modifier = Modifier
			.fillMaxSize(),
		snackbarHost = {
			SnackbarHost(GlobalState.snackbarHostState)
		}
	) {
		LaunchedEffect(Unit) {
			showSnackbar("开始学习")
		}
		val homeRouteState = remember { mutableStateOf(HomeRoute.Carousel) }
		XSidebar(
			sidebar = {
				HomeSidebar(homeRouteState)
			},
			sidebarTop = if (WindowManager.windowState.placement == WindowPlacement.Floating)
				20.dp else Dp.Hairline
		) {
			HomeContent(homeRouteState)
		}
	}
}

@Composable
private fun HomeSidebar(
	homeRouteState: MutableState<HomeRoute>,
) {
	Column(
		modifier = Modifier
			.padding(top = 12.dp)
			.padding(horizontal = 12.dp)
			.fillMaxSize()
	) {
		HomeRoute.entries.groupBy { it.group }.forEach { (group, routes) ->
			val visibleState = remember { mutableStateOf(true) }
			Spacer(modifier = Modifier.height(12.dp))
			XCollapse(
				visibleState = visibleState,
				collapse = {
					Column(
						modifier = Modifier.fillMaxWidth()
					) {
						routes.forEach {
							Item(
								homeRoute = it,
								homeRouteState = homeRouteState
							)
						}
					}
				}
			) {
				Title(group)
			}
		}
	}
}

@Composable
private fun Title(
	group: Group,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(32.dp)
			.padding(horizontal = 12.dp),
		verticalAlignment = Alignment.CenterVertically,
	) {
		Text(
			text = group.chinese,
			fontSize = 13.sp,
			color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
			textAlign = TextAlign.Center,
			fontWeight = FontWeight.Light
		)
	}
}

@Composable
private fun Item(
	homeRoute: HomeRoute,
	homeRouteState: MutableState<HomeRoute>,
) {
	val selected = homeRouteState.value == homeRoute
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(42.dp)
			.clip(RoundedCornerShape(6.dp))
			.background(if (selected) MaterialTheme.colorScheme.primary else Color.Transparent)
			.clickable {
				homeRouteState.value = homeRoute
			}
			.padding(horizontal = 12.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		val color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
		Text(
			text = homeRoute.name,
			fontSize = 15.sp,
			color = color,
			fontWeight = FontWeight.Light
		)
		Spacer(modifier = Modifier.weight(1f))
		Text(
			text = homeRoute.chinese,
			fontSize = 13.sp,
			color = color,
			fontWeight = FontWeight.Light
		)
	}
}

@Composable
private fun HomeContent(
	homeRouteState: MutableState<HomeRoute>,
) {
	Text(homeRouteState.value.name)
}