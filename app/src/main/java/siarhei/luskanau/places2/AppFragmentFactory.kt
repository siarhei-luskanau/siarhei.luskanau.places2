package siarhei.luskanau.places2

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import siarhei.luskanau.places2.data.DefaultPlaceService
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.domain.AppNavigationArgs
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.navigation.DefaultAppNavigation
import siarhei.luskanau.places2.navigation.DefaultAppNavigationArgs
import siarhei.luskanau.places2.ui.github.GithubFragment
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsFragment
import siarhei.luskanau.places2.ui.placelist.DefaultPlaceListViewModel
import siarhei.luskanau.places2.ui.placelist.PlaceListFragment
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragment
import timber.log.Timber

class AppFragmentFactory(private val activity: Activity) : FragmentFactory() {

    private val appNavigation: AppNavigation by lazy { DefaultAppNavigation(activity) }
    private val appNavigationArgs: AppNavigationArgs by lazy { DefaultAppNavigationArgs() }

    override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
        Timber.d("AppFragmentFactory:instantiate:$className")
        return when (className) {
            PlaceListFragment::class.java.name -> PlaceListFragment(
                    DefaultPlaceListViewModel(SchedulerSet.test(), DefaultPlaceService()),
                    appNavigation
            ).apply { arguments = args }

            PlaceDetailsFragment::class.java.name -> PlaceDetailsFragment(
                    appNavigation,
                    appNavigationArgs.getPlaceDetailsFragmentArgs(args)
            ).apply { arguments = args }

            PlacePhotosFragment::class.java.name -> PlacePhotosFragment(
                    appNavigation,
                    appNavigationArgs.getPlacePhotosFragmentArgs(args)
            ).apply { arguments = args }

            GithubFragment::class.java.name -> GithubFragment().apply { arguments = args }

            else -> super.instantiate(classLoader, className, args)
        }
    }
}