package siarhei.luskanau.places2.kodein

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import siarhei.luskanau.places2.kodein.di.activityModule
import siarhei.luskanau.places2.kodein.di.appModule
import siarhei.luskanau.places2.kodein.di.viewModelModule
import timber.log.Timber

class AppApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@AppApplication))
        import(appModule)
        import(activityModule)
        import(viewModelModule)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
