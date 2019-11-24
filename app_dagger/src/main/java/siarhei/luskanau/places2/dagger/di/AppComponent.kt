package siarhei.luskanau.places2.dagger.di

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import siarhei.luskanau.places2.dagger.AppApplication

@Component(modules = [
    AppModule::class,
    ActivityBuildersModule::class
])
@Singleton
interface AppComponent {

    fun inject(app: AppApplication)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: AppApplication): Builder
    }
}
