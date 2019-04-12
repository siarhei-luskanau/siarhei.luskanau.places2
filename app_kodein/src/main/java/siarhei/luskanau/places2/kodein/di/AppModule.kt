package siarhei.luskanau.places2.kodein.di

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.generic.M
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
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

val appModule = Kodein.Module(name = "appModule") {
    bind() from singleton { SchedulerSet.default() }
    bind<ViewModelProvider.Factory>() with singleton { KodeinViewModelFactory(dkodein) }
    bind<AppNavigationArgs>() with singleton { DefaultAppNavigationArgs() }
    bind<PlaceService>() with singleton { DefaultPlaceService() }
}

val activityModule = Kodein.Module(name = "activityModule") {
    bind<AppNavigation>() with factory { activity: FragmentActivity ->
        DefaultAppNavigation(activity)
    }
    bind<FragmentFactory>() with factory { activity: FragmentActivity ->
        KodeinFragmentFactory(activity, instance(arg = activity), dkodein)
    }

    // PlaceList
    bind<Fragment>(tag = PlaceListFragment::class.simpleName) with factory { activity: FragmentActivity, appNavigation: AppNavigation ->
        PlaceListFragment { instance(arg = M(activity, appNavigation)) }
    }
    bind<PlaceListPresenter>() with factory { activity: FragmentActivity, appNavigation: AppNavigation ->
        val viewModelFactory: ViewModelProvider.Factory = instance()
        val viewModel = ViewModelProviders.of(activity, viewModelFactory)
            .get(PlaceListViewModel::class.java)
        DefaultPlaceListPresenter(viewModel, appNavigation)
    }

    // PlaceDetails
    bind<Fragment>(tag = PlaceDetailsFragment::class.simpleName) with factory { _: FragmentActivity, appNavigation: AppNavigation ->
        PlaceDetailsFragment { args: Bundle? ->
            val appNavigationArgs: AppNavigationArgs = instance()
            val placeId = appNavigationArgs.getPlaceDetailsFragmentArgs(args)
            instance(arg = M(appNavigation, placeId))
        }
    }
    bind() from factory { appNavigation: AppNavigation, placeId: String ->
        PlaceDetailsPresenter(appNavigation, placeId)
    }

    // PlacePhotos
    bind<Fragment>(tag = PlacePhotosFragment::class.simpleName) with factory { _: FragmentActivity, appNavigation: AppNavigation ->
        PlacePhotosFragment { args: Bundle? ->
            val appNavigationArgs: AppNavigationArgs = instance()
            val placeId = appNavigationArgs.getPlacePhotosFragmentArgs(args)
            instance(arg = M(appNavigation, placeId))
        }
    }
    bind() from factory { appNavigation: AppNavigation, placeId: String ->
        PlacePhotosPresenter(appNavigation, placeId)
    }

    // Github
    bind<Fragment>(tag = GithubFragment::class.simpleName) with factory { _: FragmentActivity, _: AppNavigation ->
        @Suppress("RedundantLambdaArrow") val fragment = GithubFragment { _: Bundle? ->
            instance()
        }
        fragment
    }
    bind() from provider { GithubPresenter() }
}

val viewModelModule = Kodein.Module(name = "viewModelModule") {
    bind<ViewModel>(tag = PlaceListViewModel::class.simpleName) with provider {
        PlaceListViewModel(instance(), instance())
    }
}