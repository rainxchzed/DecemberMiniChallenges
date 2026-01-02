package zed.rainxch.decemberminichallenges_.snow_globe_shake.presentation

import androidx.compose.ui.unit.DpSize

data class SnowGlobeShakeState(
    val isSnowing: Boolean = false,
    val animationKey: Int = 0
)

data class SnowFlake(
    val x: Float,
    val y: Float,
    val shape: SnowFlakeShape,
    val size: DpSize,
)

enum class SnowFlakeShape {
    Dot,
    Snowflake
}