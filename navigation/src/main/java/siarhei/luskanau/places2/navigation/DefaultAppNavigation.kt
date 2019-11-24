package siarhei.luskanau.places2.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.ui.permissions.PermissionsFragmentDirections
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsFragmentDirections
import siarhei.luskanau.places2.ui.placelist.PlaceListFragmentDirections
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragmentDirections

class DefaultAppNavigation(private val activity: Activity) : AppNavigation {

    private val navController: NavController by lazy {
        Navigation.findNavController(activity, R.id.navHostFragment)
    }

    override fun goPermissionsToPlaceList() {
        val direction = PermissionsFragmentDirections.actionPermissionsToPlaceList()
        navController.navigate(direction)
    }

    override fun goToPlaceList() {
        val direction = PlacePhotosFragmentDirections.actionPlaceDetailsToPlaceList()
        navController.navigate(direction)
    }

    override fun goToPlaceDetails(placeId: String) {
        val direction = PlaceListFragmentDirections.actionPlaceListToDetails(placeId)
        navController.navigate(direction)
    }

    override fun goToPlacePhotos(placeId: String) {
        val direction = PlaceDetailsFragmentDirections.actionPlaceDetailsToPhotos(placeId)
        navController.navigate(direction)
    }
}
