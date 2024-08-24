package cn.vividcode.compose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 项目名称：vividcode-compose
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/24 21:10
 *
 * 文件介绍：HomePage
 */
@Composable
fun HomePage() {
	Box(
		modifier = Modifier.fillMaxSize()
			.background(MaterialTheme.colorScheme.onBackground)
	)
}