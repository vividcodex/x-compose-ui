package cn.vividcode.compose.sample.home

import androidx.compose.runtime.mutableStateOf

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/30 00:14
 *
 * 文件介绍：HomeState
 */
object HomeState {
	
	val itemState = mutableStateOf(HomeItem.AppComponents.item)
}