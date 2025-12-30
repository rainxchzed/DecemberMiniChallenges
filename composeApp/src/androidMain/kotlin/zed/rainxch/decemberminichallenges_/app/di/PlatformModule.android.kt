package zed.rainxch.decemberminichallenges_.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import zed.rainxch.decemberminichallenges_.core.utils.AndroidAudioPlayer
import zed.rainxch.decemberminichallenges_.core.utils.AudioPlayer

actual val platformModule: Module = module {
    single<AudioPlayer> {
        AndroidAudioPlayer(context = androidContext())
    }
}