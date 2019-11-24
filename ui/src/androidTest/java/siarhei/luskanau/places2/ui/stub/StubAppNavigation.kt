package siarhei.luskanau.places2.ui.stub

import siarhei.luskanau.places2.domain.AppNavigation

class StubAppNavigation : AppNavigation {

    override fun goPermissionsToPlaceList() {}

    override fun goToPlaceList() {}

    override fun goToPlaceDetails(placeId: String) {}

    override fun goToPlacePhotos(placeId: String) {}
}
