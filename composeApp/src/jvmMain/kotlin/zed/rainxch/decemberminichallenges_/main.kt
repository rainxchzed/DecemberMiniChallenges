package zed.rainxch.decemberminichallenges_

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import zed.rainxch.decemberminichallenges_.app.di.initKoin

fun main() = application {
    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "DecemberMiniChallenges_",
    ) {
        App()
    }
}