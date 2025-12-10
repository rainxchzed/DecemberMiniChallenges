package zed.rainxch.decemberminichallenges_.app.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import zed.rainxch.decemberminichallenges_.santa_piano.presentation.SantaPianoViewModel

val sharedModule = module {
    viewModelOf(::SantaPianoViewModel)
}