package siarhei.luskanau.places2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.ui.placelist.PlaceListViewModel
import timber.log.Timber

class AppViewModelFactory(
    val schedulerSet: SchedulerSet,
    val placeService: PlaceService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Timber.d("AppViewModelFactory:instantiate:$modelClass")
        return when {
            PlaceListViewModel::class.java.isAssignableFrom(modelClass) -> PlaceListViewModel(schedulerSet, placeService) as T
            else -> modelClass.getConstructor().newInstance()
        }
    }
}