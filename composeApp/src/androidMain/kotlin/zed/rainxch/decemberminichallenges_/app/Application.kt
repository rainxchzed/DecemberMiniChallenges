package zed.rainxch.decemberminichallenges_.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import zed.rainxch.decemberminichallenges_.app.di.initKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@Application)
        }
    }
}