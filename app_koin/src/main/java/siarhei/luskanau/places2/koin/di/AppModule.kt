package siarhei.luskanau.places2.koin.di

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.LifecycleOwner
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import siarhei.luskanau.places2.data.StubPlaceService
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.domain.AppNavigationArgs
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.navigation.DefaultAppNavigation
import siarhei.luskanau.places2.navigation.DefaultAppNavigationArgs
import siarhei.luskanau.places2.ui.github.GithubFragment
import siarhei.luskanau.places2.ui.permissions.PermissionsFragment
import siarhei.luskanau.places2.ui.permissions.PermissionsPresenter
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsFragment
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsPresenter
import siarhei.luskanau.places2.ui.placelist.DefaultPlaceListPresenter
import siarhei.luskanau.places2.ui.placelist.PlaceListFragment
import siarhei.luskanau.places2.ui.placelist.PlaceListPresenter
import siarhei.luskanau.places2.ui.placelist.PlaceListViewModel
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragment
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosPresenter
import timber.log.Timber

val appModule = module {
    single<AppNavigationArgs> { DefaultAppNavigationArgs() }
    single { SchedulerSet.default() }
    single<PlaceService> { StubPlaceService() }
}

val activityModule = module {
    factory<AppNavigation> { (activity: FragmentActivity) -> DefaultAppNavigation(activity) }
    factory<FragmentFactory> { (activity: FragmentActivity) ->
        val appNavigation: AppNavigation = get { parametersOf(activity) }
        KoinFragmentFactory(activity, appNavigation)
    }

    // Permissions
    factory { (_: LifecycleOwner, appNavigation: AppNavigation) ->
        PermissionsFragment { get { parametersOf(appNavigation) } }
    }
    factory { (appNavigation: AppNavigation) ->
        PermissionsPresenter(appNavigation)
    }

    // PlaceList
    factory { (lifecycleOwner: LifecycleOwner, appNavigation: AppNavigation) ->
        PlaceListFragment {
            get { parametersOf(lifecycleOwner, appNavigation) }
        }
    }
    factory<PlaceListPresenter> { (lifecycleOwner: LifecycleOwner, appNavigation: AppNavigation) ->
        DefaultPlaceListPresenter(lifecycleOwner.getViewModel(), appNavigation)
    }

    // PlaceDetails
    factory { (_: LifecycleOwner, appNavigation: AppNavigation) ->
        PlaceDetailsFragment { args: Bundle? ->
            val appNavigationArgs: AppNavigationArgs = get()
            val placeId = appNavigationArgs.getPlaceDetailsFragmentArgs(args)
            get { parametersOf(appNavigation, placeId) }
        }
    }
    factory { (appNavigation: AppNavigation, placeId: String) ->
        PlaceDetailsPresenter(appNavigation, placeId)
    }

    // PlacePhotos
    factory { (_: LifecycleOwner, appNavigation: AppNavigation) ->
        PlacePhotosFragment { args: Bundle? ->
            val appNavigationArgs: AppNavigationArgs = get()
            val placeId = appNavigationArgs.getPlacePhotosFragmentArgs(args)
            get { parametersOf(appNavigation, placeId) }
        }
    }
    factory { (appNavigation: AppNavigation, placeId: String) ->
        PlacePhotosPresenter(appNavigation, placeId)
    }

    // Github
    factory { GithubFragment { get() } }
    factory { Any() }
}

val viewModelModule = module {
    viewModel {
        Timber.d("KoinViewModelFactory:${PlaceListViewModel::class.java.name}")
        PlaceListViewModel(get(), get())
    }
}
