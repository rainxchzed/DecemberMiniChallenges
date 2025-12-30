package zed.rainxch.decemberminichallenges_.santa_piano.data.repository

import zed.rainxch.decemberminichallenges_.core.utils.AudioPlayer
import zed.rainxch.decemberminichallenges_.santa_piano.domain.model.PianoKey
import zed.rainxch.decemberminichallenges_.santa_piano.domain.repository.SantaPianoRepository

class SantaPianoRepositoryImpl(
    private val audioPlayer: AudioPlayer
) : SantaPianoRepository {
    override suspend fun playSound(pianoKey: PianoKey) {
        audioPlayer.play(pianoKey.musicPath)
    }
}