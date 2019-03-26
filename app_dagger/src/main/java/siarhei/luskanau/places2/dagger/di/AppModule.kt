package siarhei.luskanau.places2.dagger.di

import dagger.Module
import dagger.Provides
import siarhei.luskanau.places2.dagger.di.viewmodel.ViewModelBinderModule
import siarhei.luskanau.places2.dagger.di.viewmodel.ViewModelBuilderModule
import siarhei.luskanau.places2.data.DefaultPlaceService
import siarhei.luskanau.places2.domain.AppNavigationArgs
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.navigation.DefaultAppNavigationArgs
import javax.inject.Singleton

@Module(includes = [
    ViewModelBinderModule::class,
    ViewModelBuilderModule::class
])
class AppModule {

    @Provides
    @Singleton
    fun provideAppNavigation(): AppNavigationArgs = DefaultAppNavigationArgs()

    @Provides
    @Singleton
    fun provideSchedulerSet(): SchedulerSet = SchedulerSet.default()

    @Provides
    @Singleton
    fun providePlaceService(): PlaceService = DefaultPlaceService()
}