package cn.vividcode.compose.ui.utils

import kotlinx.coroutines.delay

/**
 * 定时器
 */
suspend fun timer(
	delay: Long = 1000L,
	period: Long = delay,
	cancel: (times: Int) -> Boolean = { false },
	reset: (times: Int) -> Boolean = { false },
	task: suspend (times: Int) -> Unit,
) {
	delay(delay)
	var times = 0
	while (true) {
		if (cancel(times)) {
			break
		}
		if (reset(times)) {
			times = 0
			delay(period)
			continue
		}
		task(times)
		delay(period)
		times++
	}
}