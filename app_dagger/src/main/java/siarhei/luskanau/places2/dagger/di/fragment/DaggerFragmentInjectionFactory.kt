package siarhei.luskanau.places2.dagger.di.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import siarhei.luskanau.places2.dagger.di.common.PerActivity
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

@PerActivity
class DaggerFragmentInjectionFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(
        classLoader: ClassLoader,
        className: String
    ): Fragment {
        Timber.d("DaggerFragmentInjectionFactory.instantiate: $className")
        try {
            val fragmentClass = loadFragmentClass(classLoader, className)
            val fragment: Fragment = providers[fragmentClass]?.get()
                    ?: super.instantiate(classLoader, className)
            return fragment
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}