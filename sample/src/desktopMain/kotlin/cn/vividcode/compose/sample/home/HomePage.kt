package cn.vividcode.compose.sample.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.vividcode.compose.sample.GlobalState
import cn.vividcode.compose.sample.theme.ColorMode
import cn.vividcode.compose.sample.theme.DynamicThemeState
import cn.vividcode.compose.ui.foundation.icon.XIcon
import cn.vividcode.compose.ui.layout.sidebar.XSidebar
import cn.vividcode.compose.ui.layout.tree.XTree

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
				targetValue = if (DynamicThemeState.isDarkTheme) -90f else 0f,
				animationSpec = spring(stiffness = 1000f)
			)
			XIcon(
				icon = if (DynamicThemeState.isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
				rotate = rotate,
				tintColor = MaterialTheme.colorScheme.onPrimaryContainer,
				backgroundColor = MaterialTheme.colorScheme.primaryContainer,
				shadowElevation = 4.dp
			) {
				DynamicThemeState.currentMode = if (DynamicThemeState.isDarkTheme) ColorMode.Light else ColorMode.Dark
			}
		},
		floatingActionButtonPosition = FabPosition.Start
	) {
		XSidebar(
			modifier = Modifier.padding(it),
			sidebar = {
				XTree(
					groups = HomeItem.treeGroups,
					itemState = HomeState.itemState,
					modifier = Modifier.padding(top = 36.dp)
				)
			}
		) {
			Content()
		}
	}
}

@Composable
private fun Content() {
	Column {
		TitleBar()
	}
}

@Composable
private fun TitleBar() {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(70.dp)
			.shadow(
				elevation = 2.dp,
				spotColor = MaterialTheme.colorScheme.primary,
				ambientColor = MaterialTheme.colorScheme.primary
			)
			.background(MaterialTheme.colorScheme.background)
			.padding(horizontal = 16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.End,
	) {
		val item = HomeState.itemState.value
		if (item.icon != null) {
			XIcon(
				icon = item.icon!!,
				size = 24.dp,
				padding = Dp.Hairline,
				tintColor = MaterialTheme.colorScheme.primary,
			)
			Spacer(modifier = Modifier.width(8.dp))
		}
		Text(
			text = item.alias ?: item.name,
			color = MaterialTheme.colorScheme.primary
		)
	}
}