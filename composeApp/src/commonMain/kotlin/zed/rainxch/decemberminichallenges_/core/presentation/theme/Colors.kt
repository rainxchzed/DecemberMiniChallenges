package zed.rainxch.decemberminichallenges_.core.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object SantaPianoColors {
    val background = Color(0xff238CFF)
    val title = Color(0xff00369A)
    val keyHover = Brush.linearGradient(
        listOf(
            Color(0xffD0FFFD),
            Color(0xff88B5F4),
        )
    )
}

object GreetingEditor {
    val goldenEveGradient = Brush.radialGradient(
        listOf(
            Color(0xffD77C20),
            Color(0xffCA5819),
            Color(0xff943112),
        )
    )
    val snowyNightGradient = Brush.radialGradient(
        listOf(
            Color(0xff7D87F4),
            Color(0xff5A63ED),
            Color(0xff2746AE),
        )
    )
    val evergreenWish = Color(0xff244008)
    val frostyLight = Color(0xffFCFCFF)

    val text = Color(0xff0D1040)
    val strockDark = Color(0xffBFC1E2)
    val strockLight = Color(0xffFFFFFF)
    val whiteBackground = Color(0xffFFFFFF)
}