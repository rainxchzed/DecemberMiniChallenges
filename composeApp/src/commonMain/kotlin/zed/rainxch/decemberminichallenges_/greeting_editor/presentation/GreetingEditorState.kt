package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground

data class GreetingEditorState(
    val selectedBackground: GreetingBackground = GreetingBackground.GoldenEve,
    val isSelectBackgroundExpanded: Boolean = false
)