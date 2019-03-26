package siarhei.luskanau.places2.dagger.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import siarhei.luskanau.places2.dagger.di.common.ViewModelKey
import siarhei.luskanau.places2.ui.placelist.PlaceListViewModel

@Module
abstract class ViewModelBinderModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PlaceListViewModel::class)
    abstract fun bindDefaultPlaceListViewModel(viewModel: PlaceListViewModel): ViewModel
}
