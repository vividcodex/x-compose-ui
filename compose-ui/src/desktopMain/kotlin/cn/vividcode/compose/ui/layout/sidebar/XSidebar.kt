package cn.vividcode.compose.ui.layout.sidebar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import cn.vividcode.compose.ui.foundation.icon.XIcon

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
	modifier: Modifier = Modifier,
	sidebarState: MutableState<Boolean> = remember { mutableStateOf(false) },
	sidebarWidth: Dp = 250.dp,
	sidebar: @Composable BoxScope.() -> Unit,
	fixedContent: @Composable BoxScope.() -> Unit = {},
	content: @Composable BoxScope.() -> Unit,
) {
	var showOpenButton by remember { mutableStateOf(false) }
	var showCloseButton by remember { mutableStateOf(false) }
	var height by remember { mutableStateOf(0) }
	Box(
		modifier = modifier
			.fillMaxSize()
			.onGloballyPositioned { height = it.size.height }
			.pointerInput(Unit) {
				awaitPointerEventScope {
					while (true) {
						val event = awaitPointerEvent()
						val position = event.changes.first().position
						showOpenButton = !sidebarState.value &&
							position.x in 0f .. 75.dp.toPx() &&
							position.y in height / 2f - 100.dp.toPx() .. height / 2f + 100.dp.toPx()
						showCloseButton = sidebarState.value &&
							position.x in (sidebarWidth - 75.dp).toPx() .. sidebarWidth.toPx() &&
							position.y in height / 2f - 100.dp.toPx() .. height / 2f + 100.dp.toPx()
					}
				}
			}
	) {
		Box(
			modifier = Modifier
				.width(sidebarWidth)
				.fillMaxHeight()
				.background(MaterialTheme.colorScheme.surfaceContainer)
		) {
			sidebar()
			CloseButton(
				visible = showCloseButton,
				sidebarState = sidebarState
			)
		}
		val offsetX by animateDpAsState(if (sidebarState.value) sidebarWidth else Dp.Hairline)
		Box(
			modifier = Modifier
				.padding(start = offsetX)
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.surface)
		) {
			content()
			OpenButton(
				visible = showOpenButton,
				sidebarState = sidebarState
			)
		}
		fixedContent()
	}
}

@Composable
private fun BoxScope.OpenButton(
	visible: Boolean,
	sidebarState: MutableState<Boolean>,
) {
	val offsetX by animateDpAsState(if (visible) 20.dp else -(60.dp))
	XIcon(
		modifier = Modifier
			.align(Alignment.CenterStart)
			.offset(x = max(Dp.Hairline, offsetX)),
		icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
		size = 60.dp + min(Dp.Hairline, offsetX),
		padding = 10.dp,
		clip = CircleShape,
		tintColor = MaterialTheme.colorScheme.onSurface,
		backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest
	) {
		if (!sidebarState.value) {
			sidebarState.value = true
		}
	}
}

@Composable
private fun BoxScope.CloseButton(
	visible: Boolean,
	sidebarState: MutableState<Boolean>,
) {
	val offsetX by animateDpAsState(if (visible) (-20).dp else 60.dp)
	XIcon(
		modifier = Modifier
			.align(Alignment.CenterEnd)
			.offset(x = min(Dp.Hairline, offsetX)),
		icon = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
		size = min(60.dp, 60.dp - offsetX),
		padding = 10.dp,
		clip = CircleShape,
		tintColor = MaterialTheme.colorScheme.onSurfaceVariant,
		backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest
	) {
		if (sidebarState.value) {
			sidebarState.value = false
		}
	}
}