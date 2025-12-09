package zed.rainxch.decemberminichallenges_

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DecemberMiniChallenges_",
    ) {
        App()
    }
}