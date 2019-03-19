package siarhei.luskanau.places2.ui.placelist

import siarhei.luskanau.places2.domain.Place

sealed class PlaceListState

object EmptyState : PlaceListState()

data class NormalState(val placeList: List<Place>) : PlaceListState()

data class ErrorState(val error: Throwable) : PlaceListState()
