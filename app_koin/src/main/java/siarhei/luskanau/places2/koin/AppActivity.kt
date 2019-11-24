package siarhei.luskanau.places2.koin

import androidx.fragment.app.FragmentFactory
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.places2.navigation.NavigationActivity

class AppActivity : NavigationActivity() {

    override fun getAppFragmentFactory(): FragmentFactory =
            get { parametersOf(this) }
}
