import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.jetbrainsCompose)
	alias(libs.plugins.compose.compiler)
}

kotlin {
	jvm("desktop")
	
	sourceSets {
		val desktopMain by getting
		
		commonMain.dependencies {
			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.ui)
			implementation(compose.uiTooling)
			implementation(compose.materialIconsExtended)
			implementation(compose.animation)
			implementation(compose.components.resources)
			implementation(compose.components.uiToolingPreview)
			implementation(projects.composeUi)
		}
		desktopMain.dependencies {
			implementation(compose.desktop.currentOs)
			implementation(libs.kotlinx.coroutines.swing)
		}
	}
}


compose.desktop {
	application {
		mainClass = "cn.vividcode.compose.ui.MainKt"
		
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "cn.vividcode.x.compose.ui"
			packageVersion = "1.0.0"
		}
	}
}
