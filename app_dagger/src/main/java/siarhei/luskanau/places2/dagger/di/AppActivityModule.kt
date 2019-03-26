package siarhei.luskanau.places2.dagger.di

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import siarhei.luskanau.places2.dagger.AppActivity
import siarhei.luskanau.places2.dagger.di.common.PerActivity
import siarhei.luskanau.places2.dagger.di.fragment.FragmentBindingModule
import siarhei.luskanau.places2.dagger.di.fragment.FragmentBuilderModule
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.navigation.DefaultAppNavigation

@Module(includes = [
    FragmentBindingModule::class,
    FragmentBuilderModule::class
])
class AppActivityModule {

    @Provides
    @PerActivity
    fun provideAppCompatActivity(activity: AppActivity): FragmentActivity = activity

    @Provides
    @PerActivity
    fun provideAppNavigation(activity: FragmentActivity): AppNavigation = DefaultAppNavigation(activity)
}