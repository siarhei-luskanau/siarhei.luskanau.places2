package siarhei.luskanau.places2.dagger.di.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider
import siarhei.luskanau.places2.dagger.di.common.PerActivity
import timber.log.Timber

@PerActivity
class DaggerFragmentFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(
        classLoader: ClassLoader,
        className: String
    ): Fragment =
        try {
            Timber.d("DaggerFragmentFactory.instantiate: $className")
            loadFragmentClass(classLoader, className).let { fragmentClass ->
                providers[fragmentClass]?.get()
                    ?: super.instantiate(classLoader, className)
            }
        } catch (e: Throwable) {
            throw RuntimeException(e)
        }
}
