package siarhei.luskanau.places2.kodein

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import kotlin.test.assertEquals
import org.junit.Test

class KoinAppAndroidTest {

    @Test
    fun checkApplicationId() {
        assertEquals(
                expected = "siarhei.luskanau.places2.kodein",
                actual = BuildConfig.APPLICATION_ID
        )
    }

    @Test
    fun useApplicationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.kodein",
                actual = ApplicationProvider.getApplicationContext<Context>().packageName
        )
    }

    @Test
    fun useInstrumentationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.kodein.test",
                actual = InstrumentationRegistry.getInstrumentation().context.packageName
        )
    }
}
