package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground

sealed interface GreetingEditorAction {
    data object OnDismissBackgroundsDropdown : GreetingEditorAction
    data object OnExpandBackgroundsDropdown : GreetingEditorAction
    data class OnSelectGreetingBackground(val background: GreetingBackground) : GreetingEditorAction
}