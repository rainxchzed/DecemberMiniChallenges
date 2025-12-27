package zed.rainxch.decemberminichallenges_.santa_piano.presentation

import zed.rainxch.decemberminichallenges_.santa_piano.domain.model.PianoKey

sealed interface SantaPianoAction {
    data class OnPianoKeyClick(val pianoKey: PianoKey) : SantaPianoAction
}