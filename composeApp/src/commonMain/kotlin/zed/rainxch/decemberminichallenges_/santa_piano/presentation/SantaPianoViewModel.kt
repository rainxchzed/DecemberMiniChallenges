package zed.rainxch.decemberminichallenges_.santa_piano.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.decemberminichallenges_.santa_piano.domain.model.PianoKey
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
                    handleKeyPress(action.pianoKey)
                }
            }
        }
    }

    private fun handleKeyPress(pianoKey: PianoKey) {
        _state.update { it.copy(pressedKeys = it.pressedKeys + pianoKey) }

        viewModelScope.launch {
            santaPianoRepository.playSound(pianoKey)
        }

        viewModelScope.launch {
            delay(300)
            _state.update { it.copy(pressedKeys = it.pressedKeys - pianoKey) }
        }
    }

}