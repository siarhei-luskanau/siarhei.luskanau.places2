package siarhei.luskanau.places2.ui.placephotos

import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.ui.common.BasePresenter

class PlacePhotosPresenter(
    private val appNavigation: AppNavigation,
    private val placeId: String
) : BasePresenter {

    fun onPhotoClicked() {
        appNavigation.goToPlaceList()
    }
}