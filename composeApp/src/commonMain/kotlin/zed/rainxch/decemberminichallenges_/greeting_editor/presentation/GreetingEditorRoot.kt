package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.decemberminichallenges_.core.presentation.theme.montserratFontFamily

@Composable
fun GreetingEditorRoot(
    viewModel: GreetingEditorViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GreetingEditorScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun GreetingEditorScreen(
    state: GreetingEditorState,
    onAction: (GreetingEditorAction) -> Unit,
) {
}

@Preview
@Composable
private fun Preview() {
    GreetingEditorScreen(
        state = GreetingEditorState(),
        onAction = {}
    )
}
