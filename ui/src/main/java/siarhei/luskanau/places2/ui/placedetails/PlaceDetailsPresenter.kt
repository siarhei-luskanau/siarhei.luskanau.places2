package siarhei.luskanau.places2.ui.placedetails

import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.ui.common.BasePresenter

class PlaceDetailsPresenter(
    private val appNavigation: AppNavigation,
    private val placeId: String
) : BasePresenter {

    fun onPhotosClicked() {
        appNavigation.goToPlacePhotos(placeId)
    }
}