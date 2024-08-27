package cn.vividcode.compose.sample.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import cn.vividcode.compose.ui.theme.XColorSchemes

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/27 13:35
 *
 * 文件介绍：DefaultColorScheme
 */
object DefaultColorSchemes : XColorSchemes {
	
	override val lightColorScheme = lightColorScheme()
	
	override val darkColorScheme = darkColorScheme()
}