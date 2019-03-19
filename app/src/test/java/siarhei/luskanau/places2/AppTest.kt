package siarhei.luskanau.places2

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object AppTest : Spek({

    val set by memoized { mutableListOf<String>() }

    describe("adding an item") {
        beforeEachTest {
            set.add("item")
        }

        it("should contain item") {
            assertEquals("item", set[0])
        }
    }
})