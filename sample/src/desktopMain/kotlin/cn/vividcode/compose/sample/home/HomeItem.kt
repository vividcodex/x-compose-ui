package cn.vividcode.compose.sample.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ViewList
import androidx.compose.material.icons.automirrored.rounded.ViewSidebar
import androidx.compose.material.icons.rounded.*
import cn.vividcode.compose.ui.layout.tree.XTreeGroup
import cn.vividcode.compose.ui.layout.tree.XTreeItem
import cn.vividcode.compose.ui.layout.tree.XTreeSubGroup

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/25 23:54
 *
 * 文件介绍：HomeRoute
 */
enum class HomeItem(
	private val group: Group,
	private val subGroup: SubGroup? = null,
	val item: XTreeItem,
) {
	AppComponents(
		group = Group.Component,
		item = XTreeItem(
			name = "All Components",
			alias = "所有组件",
			icon = Icons.Rounded.Widgets
		),
	),
	
	Icon(
		group = Group.Component,
		subGroup = SubGroup.Foundation,
		item = XTreeItem(
			name = "Icon",
			alias = "图标",
			icon = Icons.Rounded.Category
		)
	),
	
	Button(
		group = Group.Component,
		subGroup = SubGroup.Foundation,
		item = XTreeItem(
			name = "Button",
			alias = "按钮",
			icon = Icons.Rounded.AdsClick
		)
	),
	
	Switch(
		group = Group.Component,
		subGroup = SubGroup.Foundation,
		item = XTreeItem(
			name = "Switch",
			alias = "开关",
			icon = Icons.Rounded.ToggleOn
		)
	),
	
	Sidebar(
		group = Group.Component,
		subGroup = SubGroup.Layout,
		item = XTreeItem(
			name = "Sidebar",
			alias = "侧边栏",
			icon = Icons.AutoMirrored.Rounded.ViewSidebar
		)
	),
	
	Tree(
		group = Group.Component,
		subGroup = SubGroup.Layout,
		item = XTreeItem(
			name = "Tree",
			alias = "树形控件",
			icon = Icons.AutoMirrored.Rounded.ViewList
		)
	),
	
	Carousel(
		group = Group.Component,
		subGroup = SubGroup.Component,
		item = XTreeItem(
			name = "Carousel",
			alias = "轮播图",
			icon = Icons.Rounded.ViewCarousel
		)
	);
	
	companion object {
		
		val treeGroups: List<XTreeGroup> by lazy {
			HomeItem.entries.groupBy { it.group }.map {
				val items = it.value.filter { it.subGroup == null }.map { it.item }
				val subGroups = it.value.filter {
					it.subGroup != null
				}.groupBy { it.subGroup }.map {
					XTreeSubGroup(it.key!!.title, it.value.map { it.item })
				}
				XTreeGroup(it.key.title, it.key.visible, items, subGroups)
			}
		}
	}
}

private enum class Group(
	val title: String,
	val visible: Boolean = false,
) {
	Component("组件", visible = true)
}

private enum class SubGroup(
	val title: String,
) {
	Foundation("基础组件"),
	
	Layout("布局组件"),
	
	Component("功能组件")
}