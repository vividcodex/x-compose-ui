package cn.vividcode.compose.sample.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cn.vividcode.compose.sample.Route
import cn.vividcode.compose.sample.routeTo
import cn.vividcode.compose.ui.components.carousel.XCarousel
import cn.vividcode.compose.ui.components.carousel.XCarouselDefaults
import cn.vividcode.compose.ui.foundation.button.XButton
import org.jetbrains.compose.resources.painterResource
import x_compose_ui.sample.generated.resources.*

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
fun GuidePage() {
	Scaffold(
		modifier = Modifier.fillMaxSize(),
	) {
		Row(
			modifier = Modifier
				.fillMaxSize()
				.padding(it)
		) {
			XCarousel(
				modifier = Modifier
					.weight(1f)
					.fillMaxHeight(),
				items = arrayOf(
					Res.drawable.auth_image_1,
					Res.drawable.auth_image_2,
					Res.drawable.auth_image_3,
					Res.drawable.auth_image_4,
				),
				button = XCarouselDefaults.button(
					shape = CircleShape
				),
				infinite = true,
			) { _, item ->
				Image(
					painter = painterResource(item),
					contentDescription = null,
					modifier = Modifier
						.fillMaxSize(),
					contentScale = ContentScale.FillBounds
				)
			}
			LoginPage()
		}
	}
}

@Composable
private fun RowScope.LoginPage() {
	Box(
		modifier = Modifier
			.weight(1f)
			.fillMaxHeight()
	) {
		XButton(
			modifier = Modifier
				.align(Alignment.BottomCenter)
				.padding(bottom = 24.dp),
			text = "开始学习"
		) {
			routeTo(Route.HomePage)
		}
	}
}