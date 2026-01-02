package zed.rainxch.decemberminichallenges_.snow_globe_shake.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import zed.rainxch.decemberminichallenges_.snow_globe_shake.util.ShakeDetector

class SnowGlobeShakeViewModel(
    private val shakeDetector: ShakeDetector
) : ViewModel() {

    private val _state = MutableStateFlow(SnowGlobeShakeState())
    val state: StateFlow<SnowGlobeShakeState> = _state

    init {
        shakeDetector.invoke {
            if (!_state.value.isSnowing) {
                startSnowing()
            }
        }
    }

    private fun startSnowing() {
        _state.update {
            it.copy(
                isSnowing = true,
                animationKey = it.animationKey + 1
            )
        }
    }

    fun onSnowfallComplete() {
        _state.update { it.copy(isSnowing = false) }
    }
}