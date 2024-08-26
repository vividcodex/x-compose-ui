package cn.vividcode.compose.ui.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/26 12:37
 *
 * 文件介绍：XButton
 */
@Composable
fun XButton(
	modifier: Modifier = Modifier,
	text: String,
	onClick: () -> Unit,
) {
	Box(
		modifier = modifier
			.padding(horizontal = 24.dp)
			.fillMaxWidth()
			.height(48.dp)
			.clip(RoundedCornerShape(12.dp))
			.background(MaterialTheme.colorScheme.primary)
			.clickable(onClick = onClick),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = text,
			fontSize = 16.sp,
			color = MaterialTheme.colorScheme.onPrimary
		)
	}
}