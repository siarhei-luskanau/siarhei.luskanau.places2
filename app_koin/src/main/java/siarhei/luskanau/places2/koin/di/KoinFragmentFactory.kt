package siarhei.luskanau.places2.koin.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.LifecycleOwner
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.places2.domain.AppNavigation
import timber.log.Timber

class KoinFragmentFactory(
    private val lifecycleOwner: LifecycleOwner,
    private val appNavigation: AppNavigation
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Timber.d("KoinFragmentFactory:instantiate:$className")
        return try {
            val clazz = loadFragmentClass(classLoader, className).kotlin
            GlobalContext.get().koin.get(
                clazz = clazz,
                qualifier = null,
                parameters = { parametersOf(lifecycleOwner, appNavigation) }
            )
        } catch (koinThrowable: Throwable) {
            try {
                super.instantiate(classLoader, className)
            } catch (t: Throwable) {
                throw koinThrowable
            }
        }
    }
}