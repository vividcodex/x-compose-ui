package cn.vividcode.compose.ui.layout.sidebar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
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
	sidebarState: MutableState<Boolean> = remember { mutableStateOf(false) },
	sidebarWidth: Dp = 250.dp,
	sidebar: @Composable BoxScope.() -> Unit,
	content: @Composable BoxScope.() -> Unit,
) {
	var showOpenButton by remember { mutableStateOf(false) }
	var showCloseButton by remember { mutableStateOf(false) }
	var height by remember { mutableStateOf(0) }
	Box(
		modifier = Modifier
			.fillMaxSize()
			.onGloballyPositioned {
				height = it.size.height
			}
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
	}
}

@Composable
private fun BoxScope.OpenButton(
	visible: Boolean,
	sidebarState: MutableState<Boolean>,
) {
	val width by animateDpAsState(if (visible) 25.dp else Dp.Hairline)
	Box(
		modifier = Modifier
			.align(Alignment.CenterStart)
			.width(width)
			.height(100.dp)
			.clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
			.background(MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.8f))
			.clickable {
				if (!sidebarState.value) {
					sidebarState.value = true
				}
			},
		contentAlignment = Alignment.Center
	) {
		Icon(
			imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
			contentDescription = null,
			modifier = Modifier.size(28.dp),
			tint = MaterialTheme.colorScheme.onSurface
		)
	}
}

@Composable
private fun BoxScope.CloseButton(
	visible: Boolean,
	sidebarState: MutableState<Boolean>,
) {
	val width by animateDpAsState(if (visible) 25.dp else Dp.Hairline)
	Box(
		modifier = Modifier
			.align(Alignment.CenterEnd)
			.width(width)
			.height(100.dp)
			.clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
			.background(MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.8f))
			.clickable {
				if (sidebarState.value) {
					sidebarState.value = false
				}
			},
		contentAlignment = Alignment.Center
	) {
		Icon(
			imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
			contentDescription = null,
			modifier = Modifier.size(28.dp),
			tint = MaterialTheme.colorScheme.onSurfaceVariant
		)
	}
}