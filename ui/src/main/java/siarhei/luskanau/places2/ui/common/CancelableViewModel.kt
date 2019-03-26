package siarhei.luskanau.places2.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class CancelableViewModel : ViewModel() {

    private val jobs = mutableListOf<Job>()

    override fun onCleared() {
        jobs.forEach { job ->
            job.cancel()
            jobs.remove(job)
        }
        super.onCleared()
    }

    fun Job.cancelOnCleared() {
        jobs.add(this)
    }
}