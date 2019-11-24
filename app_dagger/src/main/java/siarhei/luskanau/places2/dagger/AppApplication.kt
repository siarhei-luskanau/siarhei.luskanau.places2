package siarhei.luskanau.places2.dagger

import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import siarhei.luskanau.places2.dagger.di.AppComponent
import siarhei.luskanau.places2.dagger.di.DaggerAppComponent
import timber.log.Timber

class AppApplication : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()

        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
