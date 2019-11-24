package siarhei.luskanau.places2.ui.placelist

import androidx.lifecycle.LiveData

interface PlaceListPresenter {
    fun getStateData(): LiveData<PlaceListState>
    fun requestPlaceList()
    fun onPlaceClicked(placeId: String)
}
