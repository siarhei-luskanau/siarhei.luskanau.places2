package siarhei.luskanau.places2.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BasePresenter>(
    private val presenterProvider: (arguments: Bundle?) -> T
) : Fragment() {

    protected val presenter: T by lazy { presenterProvider(arguments) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDataSources()
    }

    protected open fun observeDataSources() {
    }
}