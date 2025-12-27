package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GreetingEditorViewModel : ViewModel() {

    private var hasLoadedInitialData = false

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
        }
    }

}