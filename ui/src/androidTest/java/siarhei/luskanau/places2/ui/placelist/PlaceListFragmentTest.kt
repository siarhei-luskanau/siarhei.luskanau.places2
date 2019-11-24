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
import siarhei.luskanau.places2.domain.Place
import siarhei.luskanau.places2.ui.R

class PlaceListFragmentTest {

    lateinit var presenterProvider: (Bundle?) -> PlaceListPresenter

    private val factory = object : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return PlaceListFragment(presenterProvider)
        }
    }

    @Test
    fun testErrorState() {
        presenterProvider = {
            object : PlaceListPresenter {
                val innerStateData = MutableLiveData<PlaceListState>()
                override fun getStateData() = innerStateData
                override fun requestPlaceList() {
                    innerStateData.value = ErrorState(RuntimeException("Test Exception"))
                }

                override fun onPlaceClicked(placeId: String) {}
            }
        }

        val scenario = launchFragmentInContainer<PlaceListFragment>(factory = factory)
        scenario.moveToState(Lifecycle.State.RESUMED)

        Espresso.onView(ViewMatchers.withText("errorStateView"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("emptyStateView"))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.placeListRecyclerView))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("permissionStateView"))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun testEmptyState() {
        presenterProvider = {
            object : PlaceListPresenter {
                val innerStateData = MutableLiveData<PlaceListState>()
                override fun getStateData() = innerStateData
                override fun requestPlaceList() {
                    innerStateData.value = EmptyState
                }

                override fun onPlaceClicked(placeId: String) {}
            }
        }

        val scenario = launchFragmentInContainer<PlaceListFragment>(factory = factory)
        scenario.moveToState(Lifecycle.State.RESUMED)

        Espresso.onView(ViewMatchers.withText("emptyStateView"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("errorStateView"))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.placeListRecyclerView))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("permissionStateView"))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun testNormalState() {
        presenterProvider = {
            object : PlaceListPresenter {
                val innerStateData = MutableLiveData<PlaceListState>()
                override fun getStateData() = innerStateData
                override fun requestPlaceList() {
                    innerStateData.value = NormalState(
                        listOf(
                            Place("1"),
                            Place("2"),
                            Place("3")
                        )
                    )
                }

                override fun onPlaceClicked(placeId: String) {}
            }
        }

        val scenario = launchFragmentInContainer<PlaceListFragment>(factory = factory)
        scenario.moveToState(Lifecycle.State.RESUMED)

        Espresso.onView(ViewMatchers.withId(R.id.placeListRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("errorStateView"))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("emptyStateView"))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("permissionStateView"))
            .check(ViewAssertions.doesNotExist())
    }

    @Test
    fun testPermissionState() {
        presenterProvider = {
            object : PlaceListPresenter {
                val innerStateData = MutableLiveData<PlaceListState>()
                override fun getStateData() = innerStateData
                override fun requestPlaceList() {
                    innerStateData.value = PermissionState
                }

                override fun onPlaceClicked(placeId: String) {}
            }
        }

        val scenario = launchFragmentInContainer<PlaceListFragment>(factory = factory)
        scenario.moveToState(Lifecycle.State.RESUMED)

        Espresso.onView(ViewMatchers.withText("permissionStateView"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("errorStateView"))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withText("emptyStateView"))
            .check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.placeListRecyclerView))
            .check(ViewAssertions.doesNotExist())
    }
}
