package siarhei.luskanau.places2.domain

interface AppNavigation {

    fun goPermissionsToPlaceList()

    fun goToPlaceList()

    fun goToPlaceDetails(placeId: String)

    fun goToPlacePhotos(placeId: String)
}
