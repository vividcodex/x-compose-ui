package cn.vividcode.compose.sample.expends

import androidx.compose.ui.input.pointer.PointerIcon
import java.awt.Cursor

val PointerIcon.Companion.W_RESIZE_CURSOR: PointerIcon
	get() = _W_RESIZE_CURSOR

private var _W_RESIZE_CURSOR = PointerIcon(Cursor(Cursor.W_RESIZE_CURSOR))