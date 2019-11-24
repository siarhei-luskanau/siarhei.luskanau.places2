package siarhei.luskanau.places2

import androidx.fragment.app.FragmentFactory
import siarhei.luskanau.places2.navigation.NavigationActivity

class AppActivity : NavigationActivity() {

    override fun getAppFragmentFactory(): FragmentFactory = AppFragmentFactory(this)
}
