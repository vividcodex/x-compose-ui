package cn.vividcode.compose.ui.foundation.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/26 00:12
 *
 * 文件介绍：XIcon
 */
@Composable
fun XIcon(
	modifier: Modifier = Modifier,
	icon: ImageVector,
	size: Dp = 40.dp,
	padding: Dp = (size - size / 1.5f) / 2f,
	clip: Shape = RoundedCornerShape(size / 4),
	rotate: Float = 0f,
	tintColor: Color = MaterialTheme.colorScheme.primary,
	backgroundColor: Color = Color.Transparent,
	onClick: () -> Unit,
) {
	Icon(
		imageVector = icon,
		contentDescription = null,
		modifier = modifier
			.size(size)
			.clip(clip)
			.background(backgroundColor)
			.clickable(onClick = onClick)
			.padding(padding)
			.rotate(rotate),
		tint = tintColor
	)
}