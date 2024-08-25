package cn.vividcode.compose.sample.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cn.vividcode.compose.ui.components.XCarousel
import cn.vividcode.compose.ui.components.XCarouselButtonAlignment
import cn.vividcode.compose.ui.components.XCarouselIndicatorType
import cn.vividcode.compose.ui.components.XCarousels
import org.jetbrains.compose.resources.painterResource
import x_compose_ui.sample.generated.resources.Res
import x_compose_ui.sample.generated.resources.auth_image_1
import x_compose_ui.sample.generated.resources.auth_image_2
import x_compose_ui.sample.generated.resources.auth_image_3

/**
 * 项目名称：vividcode-compose
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/24 17:33
 *
 * 文件介绍：授权页
 */
@Composable
fun AuthPage() {
	Row(
		modifier = Modifier.fillMaxSize()
	) {
		XCarousel(
			modifier = Modifier
				.width(300.dp)
				.fillMaxHeight(),
			indicator = XCarousels.indicator(
				type = XCarouselIndicatorType.Line,
			),
			button = XCarousels.button(
				alignment = XCarouselButtonAlignment.Bottom,
				shape = CircleShape
			),
			items = arrayOf(
				Res.drawable.auth_image_1,
				Res.drawable.auth_image_2,
				Res.drawable.auth_image_3
			)
		) { _, item ->
			Image(
				painter = painterResource(item),
				contentDescription = null,
				modifier = Modifier
					.width(300.dp)
					.fillMaxHeight(),
				contentScale = ContentScale.FillBounds
			)
		}
	}
}