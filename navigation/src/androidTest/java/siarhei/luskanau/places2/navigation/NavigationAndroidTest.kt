package siarhei.luskanau.places2.navigation

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import kotlin.test.assertEquals

class NavigationAndroidTest {

    @Test
    fun checkApplicationId() {
        assertEquals(
                expected = "siarhei.luskanau.places2.navigation",
                actual = BuildConfig.APPLICATION_ID
        )
    }

    @Test
    fun useApplicationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.navigation.test",
                actual = ApplicationProvider.getApplicationContext<Context>().packageName
        )
    }

    @Test
    fun useInstrumentationContext() {
        assertEquals(
                expected = "siarhei.luskanau.places2.navigation.test",
                actual = InstrumentationRegistry.getInstrumentation().context.packageName
        )
    }
}