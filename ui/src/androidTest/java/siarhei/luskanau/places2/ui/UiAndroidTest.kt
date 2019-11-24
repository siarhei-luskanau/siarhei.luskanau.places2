package siarhei.luskanau.places2.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import kotlin.test.assertEquals
import org.junit.Test

class UiAndroidTest {

    @Test
    fun checkApplicationId() {
        assertEquals(
                expected = "siarhei.luskanau.places2.ui",
                actual = BuildConfig.LIBRARY_PACKAGE_NAME
        )
    }

    @Test
    fun useApplicationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.ui.test",
                actual = ApplicationProvider.getApplicationContext<Context>().packageName
        )
    }

    @Test
    fun useInstrumentationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.ui.test",
                actual = InstrumentationRegistry.getInstrumentation().context.packageName
        )
    }
}
