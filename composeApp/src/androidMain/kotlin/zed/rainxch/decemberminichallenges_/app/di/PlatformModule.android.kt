package zed.rainxch.decemberminichallenges_.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import zed.rainxch.decemberminichallenges_.core.utils.AndroidAudioPlayer
import zed.rainxch.decemberminichallenges_.core.utils.AudioPlayer
import zed.rainxch.decemberminichallenges_.snow_globe_shake.util.AndroidShakeDetector
import zed.rainxch.decemberminichallenges_.snow_globe_shake.util.ShakeDetector

actual val platformModule: Module = module {
    single<AudioPlayer> {
        AndroidAudioPlayer(context = androidContext())
    }

    single<ShakeDetector> {
        AndroidShakeDetector(
            context = androidContext()
        )
    }
}