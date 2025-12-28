package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextColor
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextFontFamily
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextSize

data class GreetingEditorState(
    val selectedBackground: GreetingBackground = GreetingBackground.GoldenEve,
    val isSelectBackgroundExpanded: Boolean = false,
    val isCurrentlyBold: Boolean = false,
    val isCurrentlyItalic: Boolean = false,
    val isCurrentlyUnderline: Boolean = false,
    val currentColor: TextColor = TextColor.PureWhite,
    val currentFontFamily: TextFontFamily = TextFontFamily.Montserrat,
    val currentFontSize: TextSize = TextSize.Large,
    val isSelectColorDropdownExpanded: Boolean = false,
    val isSelectFontFamilyDropdownExpanded: Boolean = false,
    val isSelectFontSizeDropdownExpanded: Boolean = false,
)