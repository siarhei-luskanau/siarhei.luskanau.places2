package siarhei.luskanau.places2.ui.permissions

import siarhei.luskanau.places2.domain.AppNavigation

class PermissionsPresenter(
    private val appNavigation: AppNavigation
) {

    fun onPermissionsGranted() {
        appNavigation.goPermissionsToPlaceList()
    }
}
