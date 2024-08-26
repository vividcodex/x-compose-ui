package cn.vividcode.compose.ui.foundation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
	icon: ImageVector,
	modifier: Modifier = Modifier,
	tint: Color = MaterialTheme.colorScheme.primary,
	onClick: () -> Unit,
) {
	Icon(
		imageVector = icon,
		contentDescription = null,
		modifier = modifier
			.size(40.dp)
			.clip(RoundedCornerShape(10.dp))
			.clickable(onClick = onClick)
			.padding(6.dp),
		tint = tint
	)
}