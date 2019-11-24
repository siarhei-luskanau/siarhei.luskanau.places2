package siarhei.luskanau.places2.ui.placelist

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import siarhei.luskanau.places2.domain.Place
import siarhei.luskanau.places2.domain.PlaceService
import siarhei.luskanau.places2.domain.SchedulerSet
import siarhei.luskanau.places2.setArchTaskExecutor

object PlaceListViewModelTest : Spek({

    setArchTaskExecutor()

    val testSchedulerSet by memoized { SchedulerSet.test() }

    val mockPlaceService by memoized { mock<PlaceService>() }

    val defaultPlaceListViewModel by memoized {
        PlaceListViewModel(
                testSchedulerSet,
                mockPlaceService
        )
    }

    describe("a PlaceListViewModelTest") {

        context("check ErrorState") {
            val observer = mock<Observer<PlaceListState>>()
            val expectedException = RuntimeException("test exception")
            beforeEachTest {
                runBlocking { given(mockPlaceService.getPlaceList()).willThrow(expectedException) }
                defaultPlaceListViewModel.stateData.observeForever(observer)
                defaultPlaceListViewModel.requestPlaceList()
            }

            it("should load place list") {
                runBlocking { verify(mockPlaceService).getPlaceList() }
            }

            it("should call observer.onChanged with ErrorState") {
                val captor = argumentCaptor<PlaceListState>()
                verify(observer, times(2)).onChanged(captor.capture())
                assertEquals(ErrorState(expectedException), captor.lastValue)
            }
        }

        context("check EmptyState") {
            val observer = mock<Observer<PlaceListState>>()
            beforeEachTest {
                runBlocking { given(mockPlaceService.getPlaceList()).willReturn(emptyList()) }
                defaultPlaceListViewModel.stateData.observeForever(observer)
                defaultPlaceListViewModel.requestPlaceList()
            }

            it("should load place list") {
                runBlocking { verify(mockPlaceService).getPlaceList() }
            }

            it("should call observer.onChanged with EmptyState") {
                val captor = argumentCaptor<PlaceListState>()
                verify(observer, times(2)).onChanged(captor.capture())
                assertEquals(EmptyState, captor.lastValue)
            }
        }

        context("check NormalState") {
            val observer = mock<Observer<PlaceListState>>()
            val expectedPlaceList = listOf(
                    Place("1"),
                    Place("2"),
                    Place("3")
            )
            beforeEachTest {
                runBlocking { given(mockPlaceService.getPlaceList()).willReturn(expectedPlaceList) }
                defaultPlaceListViewModel.stateData.observeForever(observer)
                defaultPlaceListViewModel.requestPlaceList()
            }

            it("should load place list") {
                runBlocking { verify(mockPlaceService).getPlaceList() }
            }

            it("should call observer.onChanged with NormalState") {
                val captor = argumentCaptor<PlaceListState>()
                verify(observer, times(2)).onChanged(captor.capture())
                assertEquals(NormalState(expectedPlaceList), captor.lastValue)
            }
        }
    }
})
