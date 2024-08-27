package cn.vividcode.compose.ui.foundation.switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/26 16:22
 *
 * 文件介绍：开关
 */
@Composable
fun XSwitch(
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	colors: XSwitchColors = XSwitchDefaults.colors(),
	size: XSwitchSize = XSwitchSize.Medium,
) {
	val trackColor by animateColorAsState(colors.getTrackColor(checked, enabled))
	Box(
		modifier = modifier
			.size(size.size)
			.clip(CircleShape)
			.background(trackColor)
			.clickable(enabled) { onCheckedChange(!checked) }
			.padding(2.dp),
		contentAlignment = Alignment.CenterStart
	) {
		val offsetX by animateDpAsState(
			if (checked) size.size.width - size.size.height else Dp.Hairline
		)
		val thumbColor by animateColorAsState(colors.getThumbColor(checked, enabled))
		Box(
			modifier = Modifier
				.offset(x = offsetX)
				.size(size.size.height - 4.dp)
				.clip(CircleShape)
				.background(thumbColor)
		)
	}
}

object XSwitchDefaults {
	
	@Composable
	fun colors(
		checkedThumbColor: Color = MaterialTheme.colorScheme.surface,
		checkedTrackColor: Color = MaterialTheme.colorScheme.primary,
		uncheckedThumbColor: Color = MaterialTheme.colorScheme.surface,
		uncheckedTrackColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.30f),
		disabledCheckedThumbColor: Color = checkedThumbColor
			.copy(alpha = ContentAlpha.disabled)
			.compositeOver(MaterialTheme.colorScheme.surface),
		disabledCheckedTrackColor: Color = checkedTrackColor
			.copy(alpha = ContentAlpha.disabled)
			.compositeOver(MaterialTheme.colorScheme.surface),
		disabledUncheckedThumbColor: Color = uncheckedThumbColor
			.copy(alpha = ContentAlpha.disabled)
			.compositeOver(MaterialTheme.colorScheme.surface),
		disabledUncheckedTrackColor: Color = uncheckedTrackColor
			.copy(alpha = ContentAlpha.disabled)
			.compositeOver(MaterialTheme.colorScheme.surface),
	): XSwitchColors = XSwitchColors(
		checkedThumbColor = checkedThumbColor,
		checkedTrackColor = checkedTrackColor,
		uncheckedThumbColor = uncheckedThumbColor,
		uncheckedTrackColor = uncheckedTrackColor,
		disabledCheckedThumbColor = disabledCheckedThumbColor,
		disabledCheckedTrackColor = disabledCheckedTrackColor,
		disabledUncheckedThumbColor = disabledUncheckedThumbColor,
		disabledUncheckedTrackColor = disabledUncheckedTrackColor
	)
}

/**
 * 开关颜色
 */
data class XSwitchColors(
	private val checkedThumbColor: Color,
	private val checkedTrackColor: Color,
	private val uncheckedThumbColor: Color,
	private val uncheckedTrackColor: Color,
	private val disabledCheckedThumbColor: Color,
	private val disabledCheckedTrackColor: Color,
	private val disabledUncheckedThumbColor: Color,
	private val disabledUncheckedTrackColor: Color,
) {
	
	internal fun getThumbColor(checked: Boolean, enabled: Boolean): Color = when {
		checked && enabled -> checkedThumbColor
		checked && !enabled -> disabledCheckedThumbColor
		!checked && enabled -> uncheckedThumbColor
		else -> disabledUncheckedThumbColor
	}
	
	internal fun getTrackColor(checked: Boolean, enabled: Boolean): Color = when {
		checked && enabled -> checkedTrackColor
		checked && !enabled -> disabledCheckedTrackColor
		!checked && enabled -> uncheckedTrackColor
		else -> disabledUncheckedTrackColor
	}
}

enum class XSwitchSize(
	val size: DpSize,
) {
	
	Small(DpSize(36.dp, 20.dp)),
	
	Medium(DpSize(45.dp, 25.dp)),
	
	Large(DpSize(54.dp, 30.dp)),
}