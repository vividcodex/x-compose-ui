package cn.vividcode.compose.sample.expends

import org.jetbrains.skiko.OS

/**
 * 项目名称：vividcode-compose
 *
 * 作者昵称：li-jia-wei
 *
 * 创建日期：2024/8/24 20:32
 *
 * 文件介绍：CurrentOS
 */
val CurrentOS: OS by lazy {
	val os = System.getProperty("os.name")
	when {
		os.equals("Mac OS X", ignoreCase = true) -> OS.MacOS
		os.startsWith("Win", ignoreCase = true) -> OS.Windows
		os.startsWith("Linux", ignoreCase = true) -> OS.Linux
		else -> error("Unknown OS name: $os")
	}
}