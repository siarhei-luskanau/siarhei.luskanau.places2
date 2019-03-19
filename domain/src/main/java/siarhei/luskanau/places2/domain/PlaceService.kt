package siarhei.luskanau.places2.domain

interface PlaceService {

    suspend fun getPlaceList(): List<Place>
}