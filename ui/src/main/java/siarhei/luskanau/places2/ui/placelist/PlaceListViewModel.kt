package siarhei.luskanau.places2.ui.placelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.ui.common.CancelableViewModel
import timber.log.Timber

class PlaceListViewModel(
    private val schedulerSet: SchedulerSet,
    private val placeService: PlaceService
) : CancelableViewModel() {

    val stateData = MutableLiveData<PlaceListState>()

    fun requestPlaceList() {
        viewModelScope.launch(schedulerSet.ioCoroutineContext) {
            try {
                val placeList = placeService.getPlaceList()
                viewModelScope.launch(schedulerSet.uiCoroutineContext) {
                    stateData.value = if (placeList.isNotEmpty()) {
                        NormalState(placeList)
                    } else {
                        EmptyState
                    }
                }
            } catch (error: Throwable) {
                Timber.e(error)
                viewModelScope.launch(schedulerSet.uiCoroutineContext) {
                    stateData.value = ErrorState(error)
                }
            }
        }.cancelOnCleared()
    }
}