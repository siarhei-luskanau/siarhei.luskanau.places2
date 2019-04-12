package siarhei.luskanau.places2.dagger.di.fragment

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.domain.AppNavigationArgs
import siarhei.luskanau.places2.ui.github.GithubFragment
import siarhei.luskanau.places2.ui.github.GithubPresenter
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsFragment
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsPresenter
import siarhei.luskanau.places2.ui.placelist.DefaultPlaceListPresenter
import siarhei.luskanau.places2.ui.placelist.PlaceListFragment
import siarhei.luskanau.places2.ui.placelist.PlaceListViewModel
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragment
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosPresenter

@Module
class FragmentBuilderModule {

    @Provides
    fun providePlaceListFragment(
        activity: FragmentActivity,
        viewModelFactory: ViewModelProvider.Factory,
        appNavigation: AppNavigation
    ) = PlaceListFragment {
        val placeListViewModel =
            ViewModelProviders.of(activity, viewModelFactory).get(PlaceListViewModel::class.java)
        DefaultPlaceListPresenter(
            placeListViewModel,
            appNavigation
        )
    }

    @Provides
    fun providePlaceDetailsFragment(
        appNavigation: AppNavigation,
        appNavigationArgs: AppNavigationArgs
    ) = PlaceDetailsFragment { args: Bundle? ->
        PlaceDetailsPresenter(
            appNavigation,
            appNavigationArgs.getPlaceDetailsFragmentArgs(args)
        )
    }

    @Provides
    fun providePlacePhotosFragment(
        appNavigation: AppNavigation,
        appNavigationArgs: AppNavigationArgs
    ) = PlacePhotosFragment { args: Bundle? ->
        PlacePhotosPresenter(
            appNavigation,
            appNavigationArgs.getPlacePhotosFragmentArgs(args)
        )
    }

    @Provides
    fun provideGithubFragment() =
        GithubFragment {
            GithubPresenter()
        }
}