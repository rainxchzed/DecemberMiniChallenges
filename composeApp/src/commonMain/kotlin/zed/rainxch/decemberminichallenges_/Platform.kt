package zed.rainxch.decemberminichallenges_

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform