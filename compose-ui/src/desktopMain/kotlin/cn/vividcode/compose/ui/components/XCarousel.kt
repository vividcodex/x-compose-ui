package cn.vividcode.compose.ui.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cn.vividcode.compose.ui.components.XCarouselIndicatorType.Dot
import cn.vividcode.compose.ui.components.XCarouselIndicatorType.Line
import cn.vividcode.compose.ui.utils.timer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 项目名称：x-compose-ui
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/25 01:28
 *
 * 文件介绍：轮播图
 */
@Composable
fun <T> XCarousel(
	modifier: Modifier = Modifier.fillMaxSize(),
	items: Array<T>,
	firstIndex: Int = 0,
	animationSpec: AnimationSpec<Float> = tween(durationMillis = 400),
	button: XCarouselButton = XCarousels.button(),
	indicator: XCarouselIndicator = XCarousels.indicator(),
	autoScroller: XCarouselAutoScroller = XCarousels.autoScroller(),
	infinite: Boolean = false,
	addition: @Composable BoxScope.(index: Int, item: T) -> Unit = { _, _ -> },
	content: @Composable BoxScope.(index: Int, item: T) -> Unit,
) {
	XCarousels(
		modifier = modifier,
		items = items.toList(),
		firstIndex = firstIndex,
		animationSpec = animationSpec,
		button = button,
		indicator = indicator,
		autoScroller = autoScroller,
		infinite = infinite,
		addition = addition,
		content = content,
	)
}

/**
 * 轮播图
 */
@Composable
fun <T> XCarousels(
	modifier: Modifier = Modifier.fillMaxSize(),
	items: List<T>,
	firstIndex: Int = 0,
	animationSpec: AnimationSpec<Float> = tween(durationMillis = 400),
	button: XCarouselButton = XCarousels.button(),
	indicator: XCarouselIndicator = XCarousels.indicator(),
	autoScroller: XCarouselAutoScroller = XCarousels.autoScroller(),
	infinite: Boolean = false,
	addition: @Composable BoxScope.(index: Int, item: T) -> Unit = { _, _ -> },
	content: @Composable BoxScope.(index: Int, item: T) -> Unit,
) {
	Box(
		modifier = modifier
	) {
		val listState = rememberLazyListState(getRealIndex(infinite, items.size, firstIndex))
		val indexState = remember { mutableIntStateOf(firstIndex) }
		val widthState = remember { mutableIntStateOf(-1) }
		val autoScrollerResetState = remember { mutableStateOf(false) }
		val coroutineScope = rememberCoroutineScope()
		val pageState by remember {
			mutableStateOf(
				PageState(indexState, items.size, infinite, listState, widthState, coroutineScope, animationSpec)
			)
		}
		if (autoScroller.enabled) {
			LaunchedEffect(Unit) {
				timer(
					delay = autoScroller.internal,
					reset = {
						val reset = autoScrollerResetState.value
						autoScrollerResetState.value = false
						widthState.value == -1 || reset
					},
				) {
					pageState.moveToNext(this)
				}
			}
		}
		LazyRow(
			modifier = Modifier
				.fillMaxSize()
				.onGloballyPositioned {
					widthState.value = it.size.width
				},
			state = listState,
			userScrollEnabled = false
		) {
			items(if (infinite) Int.MAX_VALUE else items.size) {
				val width = (widthState.value / LocalDensity.current.density).dp
				Box(
					modifier = Modifier
						.let { if (width <= Dp.Hairline) it.fillMaxWidth() else it.width(width) }
						.fillMaxHeight()
				) {
					content(it, items[it % items.size])
				}
			}
		}
		if (button.enabled) {
			XCarouselButtons(
				pageState = pageState,
				button = button,
				autoScrollerResetState = autoScrollerResetState,
			)
		}
		val index = getRealIndex(infinite, items.size, indexState.value) % items.size
		if (indicator.enabled) {
			when (indicator.type) {
				Dot -> XCarouselDotIndicator(
					index = index,
					count = items.size,
					indicator = indicator,
				)
				
				Line -> XCarouselLineIndicator(
					index = index,
					count = items.size,
					indicator = indicator,
					horizontalPadding = if (button.enabled && button.alignment == XCarouselButtonAlignment.Bottom) {
						button.padding.calculateStartPadding(LayoutDirection.Ltr) + 52.dp
					} else Dp.Hairline
				)
			}
		}
		addition(index, items[index])
	}
}

private fun getRealIndex(infinite: Boolean, count: Int, offset: Int): Int {
	return if (infinite) {
		Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2) % count + offset
	} else offset
}

/**
 * 页面状态
 */
private class PageState(
	val indexState: MutableIntState,
	private val count: Int,
	private val infinite: Boolean,
	private val listState: LazyListState,
	private val widthState: MutableIntState,
	private val coroutineScope: CoroutineScope,
	private val animationSpec: AnimationSpec<Float>,
) {
	
	fun moveToNext(
		coroutineScope: CoroutineScope = this.coroutineScope,
	): Boolean {
		if (listState.isScrollInProgress ||
			((listState.firstVisibleItemIndex >= count - 1 || indexState.value >= count - 1) && !infinite) ||
			widthState.value == -1
		) return false
		coroutineScope.launch {
			indexState.value++
			listState.animateScrollBy(widthState.value.toFloat(), animationSpec)
		}
		return true
	}
	
	fun moveToLast(
		coroutineScope: CoroutineScope = this.coroutineScope,
	): Boolean {
		if (listState.isScrollInProgress ||
			((listState.firstVisibleItemIndex <= 0 || indexState.value <= 0) && !infinite) ||
			widthState.value == -1
		) return false
		coroutineScope.launch {
			indexState.value--
			listState.animateScrollBy(-widthState.value.toFloat(), animationSpec)
		}
		return true
	}
}

/**
 * 轮播图按钮
 */
@Composable
private fun BoxScope.XCarouselButtons(
	pageState: PageState,
	button: XCarouselButton,
	autoScrollerResetState: MutableState<Boolean>,
) {
	XCarouselButton(
		icon = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
		align = button.alignment.leftAlign,
		button = button
	) {
		if (pageState.moveToLast()) {
			autoScrollerResetState.value = true
		}
	}
	XCarouselButton(
		align = button.alignment.rightAlign,
		icon = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
		button = button
	) {
		if (pageState.moveToNext()) {
			autoScrollerResetState.value = true
		}
	}
}

/**
 * 轮播图按钮
 */
@Composable
private fun BoxScope.XCarouselButton(
	icon: ImageVector,
	align: Alignment,
	button: XCarouselButton,
	onClick: () -> Unit,
) {
	Box(
		modifier = Modifier
			.padding(button.padding)
			.size(52.dp)
			.align(align)
			.clip(button.shape)
			.background(Color.Black.copy(alpha = 0.3f))
			.clickable(onClick = onClick),
		contentAlignment = Alignment.Center
	) {
		Icon(
			imageVector = icon,
			contentDescription = null,
			modifier = Modifier.size(40.dp),
			tint = Color.White.copy(alpha = 0.9f)
		)
	}
}

/**
 * 轮播图指示器 - 点
 */
@Composable
private fun BoxScope.XCarouselDotIndicator(
	index: Int,
	count: Int,
	indicator: XCarouselIndicator,
) {
	Row(
		modifier = Modifier
			.align(Alignment.BottomCenter)
			.padding(
				bottom = indicator.padding.calculateBottomPadding()
			)
	) {
		repeat(count) {
			Spacer(
				modifier = Modifier
					.padding(horizontal = 3.dp)
					.size(8.dp)
					.clip(CircleShape)
					.background(
						color = if (index == it) MaterialTheme.colorScheme.surface else Color.White.copy(alpha = 0.4f),
					)
			)
		}
	}
}

/**
 * 轮播图指示器 - 线
 */
@Composable
private fun BoxScope.XCarouselLineIndicator(
	index: Int,
	count: Int,
	indicator: XCarouselIndicator,
	horizontalPadding: Dp,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.align(Alignment.BottomCenter)
			.padding(
				start = indicator.padding.calculateStartPadding(LayoutDirection.Ltr) + horizontalPadding,
				end = indicator.padding.calculateEndPadding(LayoutDirection.Ltr) + horizontalPadding,
				bottom = indicator.padding.calculateBottomPadding(),
			),
	) {
		repeat(count) {
			Spacer(
				modifier = Modifier
					.weight(1f)
					.height(3.dp)
					.padding(horizontal = 4.dp)
					.clip(RoundedCornerShape(1.dp))
					.background(
						color = if (index == it) MaterialTheme.colorScheme.surface else Color.White.copy(alpha = 0.4f),
					)
			)
		}
	}
}

/**
 * 轮播图方法
 */
object XCarousels {
	
	/**
	 * 按钮
	 */
	fun button(
		enabled: Boolean = true,
		alignment: XCarouselButtonAlignment = XCarouselButtonAlignment.Center,
		shape: Shape = RoundedCornerShape(10.dp),
		padding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
	): XCarouselButton = XCarouselButton(enabled, alignment, shape, padding)
	
	/**
	 * 指示器
	 */
	fun indicator(
		enabled: Boolean = true,
		type: XCarouselIndicatorType = Line,
		padding: PaddingValues = PaddingValues(24.dp),
	): XCarouselIndicator = XCarouselIndicator(enabled, type, padding)
	
	/**
	 * 自动滚动
	 */
	fun autoScroller(
		enabled: Boolean = true,
		internal: Long = 5000L,
	): XCarouselAutoScroller = XCarouselAutoScroller(enabled, internal)
}

/**
 * 轮播图按钮
 */
data class XCarouselButton internal constructor(
	val enabled: Boolean,
	val alignment: XCarouselButtonAlignment,
	val shape: Shape,
	val padding: PaddingValues,
)

/**
 * 轮播图按钮的方向
 */
enum class XCarouselButtonAlignment(
	val leftAlign: Alignment,
	val rightAlign: Alignment,
) {
	
	Top(Alignment.TopStart, Alignment.TopEnd),
	
	Center(Alignment.CenterStart, Alignment.CenterEnd),
	
	Bottom(Alignment.BottomStart, Alignment.BottomEnd),
}

/**
 * 轮播图指示器
 */
data class XCarouselIndicator internal constructor(
	val enabled: Boolean,
	val type: XCarouselIndicatorType,
	val padding: PaddingValues,
)

/**
 * 轮播图指示器类型
 */
enum class XCarouselIndicatorType {
	
	Dot,
	
	Line,
}

/**
 * 轮播图自动轮播
 */
data class XCarouselAutoScroller internal constructor(
	val enabled: Boolean,
	val internal: Long,
)