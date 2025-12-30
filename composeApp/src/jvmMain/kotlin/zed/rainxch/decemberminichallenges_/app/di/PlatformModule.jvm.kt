package zed.rainxch.decemberminichallenges_.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import zed.rainxch.decemberminichallenges_.core.utils.AudioPlayer
import zed.rainxch.decemberminichallenges_.core.utils.JvmAudioPlayer

actual val platformModule: Module = module {
    single<AudioPlayer> {
        JvmAudioPlayer()
    }
}