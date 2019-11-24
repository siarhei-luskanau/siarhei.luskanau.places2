package siarhei.luskanau.places2.dagger.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import siarhei.luskanau.places2.dagger.AppActivity
import siarhei.luskanau.places2.dagger.di.common.PerActivity

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBuildersModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [AppActivityModule::class])
    internal abstract fun appActivityInjector(): AppActivity
}
