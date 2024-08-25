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

fun Modifier.judge(
	judge: Boolean,
	whenTrue: Modifier.() -> Modifier,
	whenFalse: Modifier.() -> Modifier,
): Modifier {
	return this then if (judge) whenTrue() else whenFalse()
}