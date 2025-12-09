package zed.rainxch.decemberminichallenges_.santa_piano.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SantaPianoRoot(
    viewModel: SantaPianoViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SantaPianoScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SantaPianoScreen(
    state: SantaPianoState,
    onAction: (SantaPianoAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    SantaPianoScreen(
        state = SantaPianoState(),
        onAction = {}
    )
}