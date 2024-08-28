package cn.vividcode.compose.sample.expends

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

infix fun Dp.to(height: Dp): DpSize = DpSize(this, height)

/**
 * DpSize to Size
 */
@Composable
fun DpSize.toSize(): Size {
	val density = LocalDensity.current.density
	return Size(
		width = this.width.value * density,
		height = this.height.value * density
	)
}

@Composable
fun Size.toDpSize(): DpSize {
	val density = LocalDensity.current.density
	return DpSize(
		width = (this.width / density).dp,
		height = (this.height / density).dp
	)
}

fun Size.toDpSize(density: Density): DpSize {
	return DpSize(
		width = (this.width / density.density).dp,
		height = (this.height / density.density).dp
	)
}