package cn.vividcode.compose.sample.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.vividcode.compose.sample.GlobalState
import cn.vividcode.compose.sample.theme.ColorMode
import cn.vividcode.compose.sample.theme.DynamicThemeState
import cn.vividcode.compose.ui.foundation.icon.XIcon
import cn.vividcode.compose.ui.layout.collapse.XCollapse
import cn.vividcode.compose.ui.layout.sidebar.XSidebar

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
		},
		floatingActionButton = {
			val rotate by animateFloatAsState(
				targetValue = if (DynamicThemeState.isDarkTheme) 0f else 30f,
				animationSpec = spring(stiffness = 1000f)
			)
			XIcon(
				icon = if (DynamicThemeState.isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
				size = 44.dp,
				rotate = rotate,
				tintColor = MaterialTheme.colorScheme.onPrimaryContainer,
				backgroundColor = MaterialTheme.colorScheme.primaryContainer
			) {
				DynamicThemeState.currentMode = if (DynamicThemeState.isDarkTheme) ColorMode.Light else ColorMode.Dark
			}
		},
		floatingActionButtonPosition = FabPosition.Start
	) {
		val homeRouteState = remember { mutableStateOf(HomeRoute.Carousel) }
		XSidebar(
			modifier = Modifier.padding(it),
			sidebar = {
				HomeSidebar(homeRouteState)
			}
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
			.padding(top = 20.dp)
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
			.height(32.dp)
			.clip(RoundedCornerShape(6.dp))
			.background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
			.clickable {
				homeRouteState.value = homeRoute
			}
			.padding(horizontal = 12.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		val color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
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
			color = color.copy(alpha = 0.6f),
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