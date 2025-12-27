package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

data class GreetingEditorState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)