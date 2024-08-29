package cn.vividcode.compose.ui.utils

import androidx.compose.ui.Modifier

/**
 * 是否启用 Modifier
 */
fun Modifier.enabled(
	enabled: Boolean,
	modifier: Modifier.() -> Modifier,
): Modifier = if (enabled) this.modifier() else this

/**
 * 逻辑判断
 */
fun Modifier.judge(
	judge: Boolean,
	whenTrue: Modifier.() -> Modifier,
	whenFalse: Modifier.() -> Modifier,
): Modifier = if (judge) this.whenTrue() else this.whenFalse()