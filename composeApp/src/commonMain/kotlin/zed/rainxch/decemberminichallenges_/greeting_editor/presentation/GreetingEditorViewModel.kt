package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextColor
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextFontFamily
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextSize

class GreetingEditorViewModel : ViewModel() {

    private val _state = MutableStateFlow(GreetingEditorState())
    val state = _state.asStateFlow()

    fun onAction(action: GreetingEditorAction) {
        when (action) {
            is GreetingEditorAction.OnSelectGreetingBackground -> {
                _state.update {
                    it.copy(
                        selectedBackground = action.background,
                        isSelectBackgroundExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnDismissBackgroundsDropdown -> {
                _state.update {
                    it.copy(
                        isSelectBackgroundExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnExpandBackgroundsDropdown -> {
                _state.update {
                    it.copy(
                        isSelectBackgroundExpanded = true
                    )
                }
            }

            GreetingEditorAction.OnToolbarBoldClick -> {
                _state.update {
                    it.copy(
                        isCurrentlyBold = !it.isCurrentlyBold
                    )
                }
            }

            GreetingEditorAction.OnToolbarItalicClick -> {
                _state.update {
                    it.copy(
                        isCurrentlyItalic = !it.isCurrentlyItalic
                    )
                }
            }

            GreetingEditorAction.OnToolbarUnderlineClick -> {
                _state.update {
                    it.copy(
                        isCurrentlyUnderline = !it.isCurrentlyUnderline
                    )
                }
            }

            is GreetingEditorAction.OnToolbarColorChange -> {
                _state.update {
                    it.copy(
                        currentColor = action.value,
                        isSelectColorDropdownExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnToolbarColorClick -> {
                _state.update {
                    it.copy(
                        isSelectColorDropdownExpanded = true
                    )
                }
            }

            is GreetingEditorAction.OnToolbarFontFamilyChange -> {
                _state.update {
                    it.copy(
                        currentFontFamily = action.value,
                        isSelectFontFamilyDropdownExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnToolbarFontFamilyClick -> {
                _state.update {
                    it.copy(
                        isSelectFontFamilyDropdownExpanded = true
                    )
                }
            }

            is GreetingEditorAction.OnToolbarFontSizeChange -> {
                _state.update {
                    it.copy(
                        currentFontSize = action.value,
                        isSelectFontSizeDropdownExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnToolbarFontSizeClick -> {
                _state.update {
                    it.copy(
                        isSelectFontSizeDropdownExpanded = true
                    )
                }
            }

            GreetingEditorAction.OnToolbarColorDropdownDismiss -> {
                _state.update {
                    it.copy(
                        isSelectColorDropdownExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnToolbarFontFamilyDropdownDismiss -> {
                _state.update {
                    it.copy(
                        isSelectFontFamilyDropdownExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnToolbarFontSizeDropdownDismiss -> {
                _state.update {
                    it.copy(
                        isSelectFontSizeDropdownExpanded = false
                    )
                }
            }

            GreetingEditorAction.OnToolbarResetClick -> {
                _state.update {
                    it.copy(
                        isCurrentlyBold = false,
                        isCurrentlyItalic = false,
                        isCurrentlyUnderline = false,
                        currentColor = if(it.selectedBackground != GreetingBackground.FrostyLight) {
                            TextColor.PureWhite
                        } else TextColor.MidnightBlue,
                        currentFontFamily = TextFontFamily.Montserrat,
                        currentFontSize = TextSize.Large
                    )
                }
            }

            is GreetingEditorAction.UpdateToolbarFromCursor -> {
                _state.update {
                    it.copy(
                        isCurrentlyBold = action.isBold,
                        isCurrentlyItalic = action.isItalic,
                        isCurrentlyUnderline = action.isUnderline,
                        currentColor = action.color,
                        currentFontFamily = action.fontFamily,
                        currentFontSize = action.fontSize
                    )
                }
            }
        }
    }
}