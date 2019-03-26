package siarhei.luskanau.places2.ui.placelist

import androidx.lifecycle.LiveData
import siarhei.luskanau.places2.ui.common.BasePresenter

interface PlaceListPresenter : BasePresenter {
    val stateData: LiveData<PlaceListState>
    fun requestPlaceList()
    fun onPlaceClicked(placeId: String)
}