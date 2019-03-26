package siarhei.luskanau.places2.dagger.di.viewmodel

import dagger.Module
import dagger.Provides
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.ui.placelist.PlaceListViewModel

@Module
class ViewModelBuilderModule {

    @Provides
    fun providePlaceListViewModel(
        schedulerSet: SchedulerSet,
        placeService: PlaceService
    ) = PlaceListViewModel(
            schedulerSet,
            placeService
    )
}
