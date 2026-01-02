package zed.rainxch.decemberminichallenges_.app.di

import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import zed.rainxch.decemberminichallenges_.core.utils.AudioPlayer
import zed.rainxch.decemberminichallenges_.santa_piano.data.repository.SantaPianoRepositoryImpl
import zed.rainxch.decemberminichallenges_.santa_piano.domain.repository.SantaPianoRepository
import zed.rainxch.decemberminichallenges_.santa_piano.presentation.SantaPianoViewModel
import zed.rainxch.decemberminichallenges_.snow_globe_shake.presentation.SnowGlobeShakeViewModel

val sharedModule = module {
    viewModelOf(::SantaPianoViewModel)
    viewModel<SnowGlobeShakeViewModel> {
        SnowGlobeShakeViewModel(
            shakeDetector = get()
        )
    }

    single<SantaPianoRepository> {
        SantaPianoRepositoryImpl(audioPlayer = get<AudioPlayer>())
    }
}