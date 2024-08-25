package cn.vividcode.compose.sample.expends

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize

infix fun Dp.to(height: Dp): DpSize = DpSize(this, height)