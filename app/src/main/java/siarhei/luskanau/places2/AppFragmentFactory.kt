package siarhei.luskanau.places2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import siarhei.luskanau.places2.data.DefaultPlaceService
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.domain.AppNavigationArgs
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.navigation.DefaultAppNavigation
import siarhei.luskanau.places2.navigation.DefaultAppNavigationArgs
import siarhei.luskanau.places2.ui.github.GithubFragment
import siarhei.luskanau.places2.ui.github.GithubPresenter
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsFragment
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsPresenter
import siarhei.luskanau.places2.ui.placelist.DefaultPlaceListPresenter
import siarhei.luskanau.places2.ui.placelist.PlaceListFragment
import siarhei.luskanau.places2.ui.placelist.PlaceListViewModel
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragment
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosPresenter
import timber.log.Timber

class AppFragmentFactory(private val activity: FragmentActivity) : FragmentFactory() {

    private val appNavigation: AppNavigation by lazy { DefaultAppNavigation(activity) }
    private val appNavigationArgs: AppNavigationArgs by lazy { DefaultAppNavigationArgs() }
    private val schedulerSet: SchedulerSet by lazy { SchedulerSet.default() }
    private val placeService: PlaceService by lazy { DefaultPlaceService() }
    private val viewModelFactory: ViewModelProvider.Factory by lazy { AppViewModelFactory(schedulerSet, placeService) }

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Timber.d("AppFragmentFactory:instantiate:$className")
        return when (className) {
            PlaceListFragment::class.java.name -> {
                val placeListViewModel = ViewModelProviders.of(activity, viewModelFactory).get(PlaceListViewModel::class.java)
                PlaceListFragment {
                    DefaultPlaceListPresenter(
                            placeListViewModel,
                            appNavigation
                    )
                }
            }

            PlaceDetailsFragment::class.java.name ->
                PlaceDetailsFragment { args ->
                    PlaceDetailsPresenter(
                            appNavigation,
                            appNavigationArgs.getPlaceDetailsFragmentArgs(args)
                    )
                }

            PlacePhotosFragment::class.java.name ->
                PlacePhotosFragment { args ->
                    PlacePhotosPresenter(
                            appNavigation,
                            appNavigationArgs.getPlacePhotosFragmentArgs(args)
                    )
                }

            GithubFragment::class.java.name ->
                GithubFragment {
                    GithubPresenter()
                }

            else ->
                super.instantiate(classLoader, className)
        }
    }
}