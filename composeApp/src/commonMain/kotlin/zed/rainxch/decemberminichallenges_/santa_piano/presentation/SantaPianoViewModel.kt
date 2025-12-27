package zed.rainxch.decemberminichallenges_.santa_piano.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import zed.rainxch.decemberminichallenges_.santa_piano.domain.repository.SantaPianoRepository

class SantaPianoViewModel(
    private val santaPianoRepository: SantaPianoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SantaPianoState())
    val state = _state.asStateFlow()

    fun onAction(action: SantaPianoAction) {
        when (action) {
            is SantaPianoAction.OnPianoKeyClick -> {
                viewModelScope.launch {
                    santaPianoRepository.playSound(action.pianoKey)
                }
            }
        }
    }

}