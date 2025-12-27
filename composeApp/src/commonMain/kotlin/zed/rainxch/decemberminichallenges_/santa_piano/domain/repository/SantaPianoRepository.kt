package zed.rainxch.decemberminichallenges_.santa_piano.domain.repository

import zed.rainxch.decemberminichallenges_.santa_piano.domain.model.PianoKey

interface SantaPianoRepository {
    suspend fun playSound(pianoKey: PianoKey)
}