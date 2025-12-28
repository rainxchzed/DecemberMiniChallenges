package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextColor
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextFontFamily
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextSize

sealed interface GreetingEditorAction {
    data object OnDismissBackgroundsDropdown : GreetingEditorAction
    data object OnExpandBackgroundsDropdown : GreetingEditorAction
    data class OnSelectGreetingBackground(val background: GreetingBackground) : GreetingEditorAction
    data object OnToolbarBoldClick : GreetingEditorAction
    data object OnToolbarItalicClick : GreetingEditorAction
    data object OnToolbarUnderlineClick : GreetingEditorAction
    data class OnToolbarColorChange(val value: TextColor) : GreetingEditorAction
    data class OnToolbarFontFamilyChange(val value: TextFontFamily) : GreetingEditorAction
    data class OnToolbarFontSizeChange(val value: TextSize) : GreetingEditorAction
    data object OnToolbarColorClick : GreetingEditorAction
    data object OnToolbarFontFamilyClick : GreetingEditorAction
    data object OnToolbarFontSizeClick : GreetingEditorAction
    data object OnToolbarColorDropdownDismiss : GreetingEditorAction
    data object OnToolbarFontFamilyDropdownDismiss : GreetingEditorAction
    data object OnToolbarFontSizeDropdownDismiss : GreetingEditorAction
    data object OnToolbarResetClick : GreetingEditorAction
    data class UpdateToolbarFromCursor(
        val isBold: Boolean,
        val isItalic: Boolean,
        val isUnderline: Boolean,
        val color: TextColor,
        val fontFamily: TextFontFamily,
        val fontSize: TextSize
    ) : GreetingEditorAction
}