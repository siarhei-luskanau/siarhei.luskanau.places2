package siarhei.luskanau.places2.domain

interface AppNavigation {
    fun goToPlaceList()
    fun goToPlaceDetails(placeId: String)
    fun goToPlacePhotos(placeId: String)
}