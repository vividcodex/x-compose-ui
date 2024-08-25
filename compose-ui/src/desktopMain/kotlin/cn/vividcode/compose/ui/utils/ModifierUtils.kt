package cn.vividcode.compose.ui.utils

import androidx.compose.ui.Modifier

/**
 * 是否启用 Modifier
 */
fun Modifier.enabled(enabled: Boolean, modifier: Modifier.() -> Modifier): Modifier {
	return if (enabled) {
		this then modifier()
	} else this
}