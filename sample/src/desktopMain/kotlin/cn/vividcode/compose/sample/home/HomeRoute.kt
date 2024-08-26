package cn.vividcode.compose.sample.home

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/25 23:54
 *
 * 文件介绍：HomeRoute
 */
enum class HomeRoute(
	val group: Group,
	val chinese: String,
) {
	Icon(Group.Foundation, "图标"),
	
	Button(Group.Foundation, "按钮"),
	
	Switch(Group.Foundation, "开关"),
	
	Sidebar(Group.Layout, "侧边栏"),
	
	Carousel(Group.Components, "轮播图"),
}

enum class Group(
	val chinese: String,
) {
	
	Foundation("基础组件"),
	
	Layout("布局组件"),
	
	Components("功能组件")
}