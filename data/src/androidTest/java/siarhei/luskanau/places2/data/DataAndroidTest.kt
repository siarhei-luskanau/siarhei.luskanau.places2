package siarhei.luskanau.places2.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import kotlin.test.assertEquals
import org.junit.Test

class DataAndroidTest {

    @Test
    fun checkApplicationId() {
        assertEquals(
                expected = "siarhei.luskanau.places2.data",
                actual = BuildConfig.LIBRARY_PACKAGE_NAME
        )
    }

    @Test
    fun useApplicationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.data.test",
                actual = ApplicationProvider.getApplicationContext<Context>().packageName
        )
    }

    @Test
    fun useInstrumentationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.data.test",
                actual = InstrumentationRegistry.getInstrumentation().context.packageName
        )
    }
}
