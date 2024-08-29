package cn.vividcode.compose.ui.layout.tree

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FiberManualRecord
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.vividcode.compose.ui.foundation.icon.XIcon

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/29 17:53
 *
 * 文件介绍：树形控件
 */
@Composable
fun XTree(
	groups: List<XTreeGroup>,
	itemState: MutableState<XTreeItem>,
	modifier: Modifier = Modifier,
	itemHeight: Dp = 34.dp,
	itemColor: Color = MaterialTheme.colorScheme.outline,
	selectedItemColor: Color = MaterialTheme.colorScheme.onPrimary,
	selectedItemBackgroundColor: Color = MaterialTheme.colorScheme.primary,
	groupItemColor: Color = MaterialTheme.colorScheme.primary,
	lineColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
	selectedLineColor: Color = MaterialTheme.colorScheme.onPrimary,
	itemShape: Shape = RoundedCornerShape(itemHeight / 3),
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.then(modifier)
	) {
		groups.forEach {
			var visible by remember { mutableStateOf(it.visible) }
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.height(itemHeight)
					.padding(horizontal = 8.dp)
					.clip(itemShape)
					.clickable { visible = !visible }
					.padding(start = 2.dp, end = 8.dp),
				verticalAlignment = Alignment.CenterVertically,
			) {
				val rotate by animateFloatAsState(if (visible) 0f else 90f)
				XIcon(
					icon = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
					size = 20.dp,
					padding = Dp.Hairline,
					rotate = rotate,
				)
				Spacer(modifier = Modifier.width(2.dp))
				Box(
					modifier = Modifier
						.fillMaxHeight()
						.padding(bottom = 4.dp),
					contentAlignment = Alignment.CenterStart
				) {
					Text(
						text = it.name,
						fontSize = 12.sp,
						fontWeight = FontWeight.Light,
						color = groupItemColor
					)
				}
			}
			AnimatedVisibility(
				visible = visible,
				enter = expandVertically(expandFrom = Alignment.Top),
				exit = shrinkVertically(shrinkTowards = Alignment.Top)
			) {
				XTreeGroup(
					group = it,
					itemState = itemState,
					itemHeight = itemHeight,
					itemColor = itemColor,
					selectedItemColor = selectedItemColor,
					selectedItemBackgroundColor = selectedItemBackgroundColor,
					lineColor = lineColor,
					selectedLineColor = selectedLineColor,
					itemShape = itemShape
				)
			}
		}
	}
}

@Composable
private fun XTreeGroup(
	group: XTreeGroup,
	itemState: MutableState<XTreeItem>,
	itemHeight: Dp,
	itemColor: Color,
	selectedItemColor: Color,
	selectedItemBackgroundColor: Color,
	lineColor: Color,
	selectedLineColor: Color,
	itemShape: Shape,
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
	) {
		group.items.forEach {
			XItem(
				item = it,
				itemState = itemState,
				itemHeight = itemHeight,
				itemColor = itemColor,
				selectedItemColor = selectedItemColor,
				selectedItemBackgroundColor = selectedItemBackgroundColor,
				lineColor = lineColor,
				selectedLineColor = selectedLineColor,
				itemShape = itemShape
			)
		}
		group.subGroups.forEachIndexed { index, it ->
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.height(itemHeight)
					.padding(start = 10.dp, end = 16.dp),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Box(
					modifier = Modifier
						.width(20.dp)
						.fillMaxHeight()
						.clickable { }
				) {
					if (index != 0 || group.items.isNotEmpty()) {
						Spacer(
							modifier = Modifier
								.align(Alignment.TopCenter)
								.width(1.dp)
								.height(14.dp)
								.background(lineColor)
						)
					}
					XIcon(
						icon = Icons.Outlined.FiberManualRecord,
						modifier = Modifier.align(Alignment.Center),
						size = 10.dp,
						padding = Dp.Hairline,
						tintColor = lineColor
					)
					Spacer(
						modifier = Modifier
							.align(Alignment.BottomCenter)
							.width(1.dp)
							.height(14.dp)
							.background(lineColor)
					)
				}
				Box(
					modifier = Modifier
						.fillMaxHeight()
						.padding(bottom = 4.dp),
					contentAlignment = Alignment.CenterStart
				) {
					Text(
						text = it.name,
						fontSize = 11.sp,
						fontWeight = FontWeight.Light,
						color = itemColor
					)
				}
			}
			it.items.forEach {
				XItem(
					item = it,
					itemState = itemState,
					itemHeight = itemHeight,
					itemColor = itemColor,
					selectedItemColor = selectedItemColor,
					selectedItemBackgroundColor = selectedItemBackgroundColor,
					lineColor = lineColor,
					selectedLineColor = selectedLineColor,
					itemShape = itemShape
				)
			}
		}
	}
}

@Composable
private fun XItem(
	item: XTreeItem,
	itemState: MutableState<XTreeItem>,
	itemHeight: Dp,
	itemColor: Color,
	selectedItemColor: Color,
	selectedItemBackgroundColor: Color,
	lineColor: Color,
	selectedLineColor: Color,
	itemShape: Shape,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(itemHeight)
			.shadow(
				elevation = if (itemState.value == item) 2.dp else Dp.Hairline,
				shape = itemShape,
				ambientColor = selectedItemColor,
				spotColor = selectedItemColor
			)
			.padding(horizontal = 8.dp)
			.clip(itemShape)
			.background(if (itemState.value == item) selectedItemBackgroundColor else Color.Transparent)
			.clickable { itemState.value = item }
			.padding(start = 2.dp, end = 8.dp),
		verticalAlignment = Alignment.CenterVertically,
	) {
		Box(
			modifier = Modifier
				.align(Alignment.CenterVertically)
				.width(20.dp)
				.fillMaxHeight(),
			contentAlignment = Alignment.Center
		) {
			Spacer(
				modifier = Modifier
					.width(1.dp)
					.fillMaxHeight()
					.background(if (itemState.value == item) selectedLineColor else lineColor)
			)
		}
		Spacer(modifier = Modifier.width(6.5.dp))
		Box(
			modifier = Modifier
				.fillMaxHeight()
				.padding(bottom = 4.dp),
			contentAlignment = Alignment.Center,
		) {
			Text(
				text = item.name,
				fontSize = 12.sp,
				color = if (itemState.value == item) selectedItemColor else itemColor,
			)
		}
		Spacer(modifier = Modifier.weight(1f))
		
		if (item.icon != null) {
			Spacer(modifier = Modifier.width(4.dp))
			XIcon(
				icon = item.icon,
				size = 17.dp,
				padding = Dp.Hairline,
				tintColor = if (itemState.value == item) selectedItemColor.copy(alpha = 0.75f) else itemColor.copy(alpha = 0.75f)
			)
		}
		
		if (item.alias != null) {
			Spacer(modifier = Modifier.width(4.dp))
			Box(
				modifier = Modifier
					.fillMaxHeight()
					.padding(bottom = 4.dp),
				contentAlignment = Alignment.Center,
			) {
				Text(
					text = item.alias,
					fontSize = 11.sp,
					fontWeight = FontWeight.Light,
					color = if (itemState.value == item) selectedItemColor.copy(alpha = 0.75f) else itemColor.copy(alpha = 0.75f),
				)
			}
		}
	}
}

data class XTreeGroup(
	val name: String,
	val visible: Boolean = false,
	val items: List<XTreeItem> = emptyList(),
	val subGroups: List<XTreeSubGroup> = emptyList(),
)

data class XTreeSubGroup(
	val name: String,
	val items: List<XTreeItem> = emptyList(),
)

data class XTreeItem(
	val name: String,
	val alias: String? = null,
	val icon: ImageVector? = null,
)