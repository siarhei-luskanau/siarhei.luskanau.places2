package siarhei.luskanau.places2.navigation

import android.os.Bundle
import siarhei.luskanau.places2.domain.AppNavigationArgs
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsFragmentArgs
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragmentArgs

class DefaultAppNavigationArgs : AppNavigationArgs {

    override fun getPlaceDetailsFragmentArgs(args: Bundle?): String =
            PlaceDetailsFragmentArgs.fromBundle(args ?: Bundle()).placeId

    override fun getPlacePhotosFragmentArgs(args: Bundle?): String =
            PlacePhotosFragmentArgs.fromBundle(args ?: Bundle()).placeId
}
