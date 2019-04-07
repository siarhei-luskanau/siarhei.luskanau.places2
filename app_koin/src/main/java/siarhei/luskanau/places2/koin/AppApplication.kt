package siarhei.luskanau.places2.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import siarhei.luskanau.places2.koin.di.activityModule
import siarhei.luskanau.places2.koin.di.appModule
import siarhei.luskanau.places2.koin.di.viewModelModule
import timber.log.Timber

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppApplication)
            androidFileProperties()
            modules(
                    appModule,
                    activityModule,
                    viewModelModule
            )
        }
    }
}