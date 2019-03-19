package siarhei.luskanau.places2.ui.placelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.domain.Place
import siarhei.luskanau.places2.ui.stub.StubAppNavigation

class PlaceListFragmentTest {

    lateinit var placeListViewModelProvider: () -> PlaceListViewModel

    private val factory = object : FragmentFactory() {
        private val appNavigation: AppNavigation = StubAppNavigation()
        override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
            return PlaceListFragment(
                    placeListViewModelProvider.invoke(),
                    appNavigation
            ).apply { arguments = args }
        }
    }

    @Test
    fun testErrorState() {
        placeListViewModelProvider = {
            object : PlaceListViewModel {
                override val stateData = MutableLiveData<PlaceListState>()
                override fun requestPlaceList() {
                    stateData.value = ErrorState(RuntimeException("Test Exception"))
                }
            }
        }

        val scenario = launchFragmentInContainer<PlaceListFragment>(factory = factory)
        scenario.moveToState(Lifecycle.State.RESUMED)

        Espresso.onView(ViewMatchers.withText("errorStateView")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("emptyStateView")).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("normalStateView")).check(ViewAssertions.doesNotExist())
    }

    @Test
    fun testEmptyState() {
        placeListViewModelProvider = {
            object : PlaceListViewModel {
                override val stateData = MutableLiveData<PlaceListState>()
                override fun requestPlaceList() {
                    stateData.value = EmptyState
                }
            }
        }

        val scenario = launchFragmentInContainer<PlaceListFragment>(factory = factory)
        scenario.moveToState(Lifecycle.State.RESUMED)

        Espresso.onView(ViewMatchers.withText("emptyStateView")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("errorStateView")).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("normalStateView")).check(ViewAssertions.doesNotExist())
    }

    @Test
    fun testNormalState() {
        placeListViewModelProvider = {
            object : PlaceListViewModel {
                override val stateData = MutableLiveData<PlaceListState>()
                override fun requestPlaceList() {
                    stateData.value = NormalState(listOf(
                            Place("1"),
                            Place("2"),
                            Place("3")
                    ))
                }
            }
        }

        val scenario = launchFragmentInContainer<PlaceListFragment>(factory = factory)
        scenario.moveToState(Lifecycle.State.RESUMED)

        Espresso.onView(ViewMatchers.withText("normalStateView")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("errorStateView")).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("emptyStateView")).check(ViewAssertions.doesNotExist())
    }
}