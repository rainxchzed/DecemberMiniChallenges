package zed.rainxch.decemberminichallenges_

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import zed.rainxch.decemberminichallenges_.app.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(
        configure = {
            initKoin()
        }
    ) {
        App()
    }
}