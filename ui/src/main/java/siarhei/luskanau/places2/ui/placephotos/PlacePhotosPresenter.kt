package siarhei.luskanau.places2.ui.placephotos

import siarhei.luskanau.places2.domain.AppNavigation

class PlacePhotosPresenter(
    private val appNavigation: AppNavigation,
    private val placeId: String
) {

    fun onPhotoClicked() {
        appNavigation.goToPlaceList()
    }
}
