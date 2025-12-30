package zed.rainxch.decemberminichallenges_.santa_piano.presentation

import zed.rainxch.decemberminichallenges_.santa_piano.domain.model.PianoKey

data class SantaPianoState(
    val pianoKeys: List<PianoKey> = PianoKey.entries,
    val pressedKeys: Set<PianoKey> = emptySet()
)