package siarhei.luskanau.places2.ui.placelist

import androidx.lifecycle.LiveData
import siarhei.luskanau.places2.domain.AppNavigation

class DefaultPlaceListPresenter(
    private val placeListViewModel: PlaceListViewModel,
    private val appNavigation: AppNavigation
) : PlaceListPresenter {

    override val stateData: LiveData<PlaceListState> = placeListViewModel.stateData

    override fun requestPlaceList() = placeListViewModel.requestPlaceList()

    override fun onPlaceClicked(placeId: String) = appNavigation.goToPlaceDetails(placeId)
}