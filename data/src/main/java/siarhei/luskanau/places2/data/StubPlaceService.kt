package siarhei.luskanau.places2.data

import siarhei.luskanau.places2.domain.Place
import siarhei.luskanau.places2.domain.PlaceService

class StubPlaceService : PlaceService {
    override suspend fun getPlaceList(): List<Place> = emptyList()
}
