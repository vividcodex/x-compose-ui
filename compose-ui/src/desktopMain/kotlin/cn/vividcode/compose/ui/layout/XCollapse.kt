package cn.vividcode.compose.ui.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/26 18:55
 *
 * 文件介绍：折叠面板
 */
@Composable
fun XCollapse(
	visibleState: MutableState<Boolean> = remember { mutableStateOf(false) },
	collapse: @Composable BoxScope.() -> Unit,
	collapseClip: Shape = RoundedCornerShape(6.dp),
	content: @Composable BoxScope.() -> Unit,
) {
	Column(
		modifier = Modifier.fillMaxWidth()
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(min = 24.dp)
				.clip(collapseClip)
				.clickable {
					visibleState.value = !visibleState.value
				}
		) {
			content()
			val degrees by animateFloatAsState(if (!visibleState.value) 0f else 90f)
			Icon(
				imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
				contentDescription = null,
				modifier = Modifier
					.padding(end = 12.dp)
					.size(20.dp)
					.align(Alignment.CenterEnd)
					.rotate(degrees),
				tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
			)
		}
		AnimatedVisibility(
			visible = visibleState.value,
			enter = expandVertically(expandFrom = Alignment.Top),
			exit = shrinkVertically(shrinkTowards = Alignment.Top)
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
			) {
				collapse()
			}
		}
	}
}