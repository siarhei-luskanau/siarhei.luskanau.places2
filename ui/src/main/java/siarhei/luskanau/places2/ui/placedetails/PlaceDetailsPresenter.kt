package siarhei.luskanau.places2.ui.placedetails

import siarhei.luskanau.places2.domain.AppNavigation

class PlaceDetailsPresenter(
    private val appNavigation: AppNavigation,
    private val placeId: String
) {

    fun onPhotosClicked() {
        appNavigation.goToPlacePhotos(placeId)
    }
}
