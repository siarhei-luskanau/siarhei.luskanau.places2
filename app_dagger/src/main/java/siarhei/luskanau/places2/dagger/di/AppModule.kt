package siarhei.luskanau.places2.dagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import siarhei.luskanau.places2.dagger.AppApplication
import siarhei.luskanau.places2.dagger.di.viewmodel.ViewModelBinderModule
import siarhei.luskanau.places2.dagger.di.viewmodel.ViewModelBuilderModule
import siarhei.luskanau.places2.data.android.places.AndroidPlaceService
import siarhei.luskanau.places2.domain.AppNavigationArgs
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.navigation.DefaultAppNavigationArgs

@Module(
    includes = [
        ViewModelBinderModule::class,
        ViewModelBuilderModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: AppApplication): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun provideAppNavigation(): AppNavigationArgs = DefaultAppNavigationArgs()

    @Provides
    @Singleton
    fun provideSchedulerSet(): SchedulerSet = SchedulerSet.default()

    @Provides
    @Singleton
    fun providePlaceService(
        context: Context
    ): PlaceService = AndroidPlaceService(
        context
    )
}
