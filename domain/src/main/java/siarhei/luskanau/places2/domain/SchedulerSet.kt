package siarhei.luskanau.places2.domain

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class SchedulerSet(
    open val ioCoroutineContext: CoroutineContext,
    open val uiCoroutineContext: CoroutineContext
) {

    companion object {
        fun test(): SchedulerSet = SchedulerSet(
                ioCoroutineContext = Dispatchers.Unconfined,
                uiCoroutineContext = Dispatchers.Unconfined
        )

        fun default(): SchedulerSet = SchedulerSet(
                ioCoroutineContext = Dispatchers.IO,
                uiCoroutineContext = Dispatchers.Main
        )
    }
}
