package siarhei.luskanau.places2.dagger.di.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import siarhei.luskanau.places2.dagger.di.common.FragmentKey
import siarhei.luskanau.places2.ui.github.GithubFragment
import siarhei.luskanau.places2.ui.permissions.PermissionsFragment
import siarhei.luskanau.places2.ui.placedetails.PlaceDetailsFragment
import siarhei.luskanau.places2.ui.placelist.PlaceListFragment
import siarhei.luskanau.places2.ui.placephotos.PlacePhotosFragment

@Module
abstract class FragmentBindingModule {

    @Binds
    abstract fun bindFragmentFactory(factory: DaggerFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(PermissionsFragment::class)
    abstract fun bindPermissionsFragment(fragment: PermissionsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PlaceListFragment::class)
    abstract fun bindPlaceListFragment(fragment: PlaceListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PlaceDetailsFragment::class)
    abstract fun bindPlaceDetailsFragment(fragment: PlaceDetailsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PlacePhotosFragment::class)
    abstract fun bindPlacePhotosFragment(fragment: PlacePhotosFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(GithubFragment::class)
    abstract fun bindGithubFragment(fragment: GithubFragment): Fragment
}
