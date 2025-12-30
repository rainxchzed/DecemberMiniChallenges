package zed.rainxch.decemberminichallenges_.core.utils

interface AudioPlayer {
    suspend fun play(resourcePath: String)
}