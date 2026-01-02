package zed.rainxch.decemberminichallenges_.snow_globe_shake.util

interface ShakeDetector {
    fun invoke(
        onShake: () -> Unit
    )
}