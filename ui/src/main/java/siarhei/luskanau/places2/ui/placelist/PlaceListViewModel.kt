package siarhei.luskanau.places2.ui.placelist

import androidx.lifecycle.LiveData

interface PlaceListViewModel {
    val stateData: LiveData<PlaceListState>
    fun requestPlaceList()
}