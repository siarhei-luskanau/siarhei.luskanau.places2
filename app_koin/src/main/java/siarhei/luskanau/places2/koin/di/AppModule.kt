package siarhei.luskanau.places2.koin.di

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
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
import siarhei.luskanau.places2.ui.placelist.PlaceListPresenter
import siarhei.luskanau.places2.ui.placelist.PlaceListViewModel
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragment
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosPresenter

val appModule = module {
    single<AppNavigationArgs> { DefaultAppNavigationArgs() }
    single { SchedulerSet.default() }
    single<PlaceService> { DefaultPlaceService() }
}

val activityModule = module {
    factory<AppNavigation> { (activity: FragmentActivity) -> DefaultAppNavigation(activity) }
    factory<FragmentFactory> { (activity: FragmentActivity) ->
        val appNavigation: AppNavigation = get { parametersOf(activity) }
        KoinFragmentFactory(appNavigation)
    }

    // PlaceList
    factory { (appNavigation: AppNavigation) ->
        PlaceListFragment {
            get { parametersOf(appNavigation) }
        }
    }
    factory<PlaceListPresenter> { (appNavigation: AppNavigation) ->
        DefaultPlaceListPresenter(get(), appNavigation)
    }

    // PlaceDetails
    factory { (appNavigation: AppNavigation) ->
        PlaceDetailsFragment { args: Bundle? ->
            val placeId = get<AppNavigationArgs>().getPlaceDetailsFragmentArgs(args)
            get { parametersOf(appNavigation, placeId) }
        }
    }
    factory { (appNavigation: AppNavigation, placeId: String) ->
        PlaceDetailsPresenter(appNavigation, placeId)
    }

    // PlacePhotos
    factory { (appNavigation: AppNavigation) ->
        PlacePhotosFragment { args: Bundle? ->
            val placeId = get<AppNavigationArgs>().getPlacePhotosFragmentArgs(args)
            get { parametersOf(appNavigation, placeId) }
        }
    }
    factory { (appNavigation: AppNavigation, placeId: String) ->
        PlacePhotosPresenter(appNavigation, placeId)
    }

    // Github
    factory { GithubFragment { get() } }
    factory { GithubPresenter() }
}

val viewModelModule = module {
    viewModel { PlaceListViewModel(get(), get()) }
}