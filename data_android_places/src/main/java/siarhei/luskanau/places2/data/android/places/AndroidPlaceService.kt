package siarhei.luskanau.places2.data.android.places

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_WIFI_STATE
import android.content.Context
import androidx.annotation.RequiresPermission
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place.Field
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import siarhei.luskanau.places2.domain.Place
import siarhei.luskanau.places2.domain.PlaceService

class AndroidPlaceService(
    private val context: Context
) : PlaceService {

    private fun initIfNeed() {
        if (Places.isInitialized().not()) {
            val placesApiKey = context.getString(R.string.places_api_key)
            Places.initialize(context, placesApiKey)
        }
    }

    @RequiresPermission(allOf = [ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE])
    override suspend fun getPlaceList(): List<Place> = suspendCoroutine { continuation ->
        try {
            initIfNeed()

            val placesClient = Places.createClient(context)

            val currentPlaceRequest = FindCurrentPlaceRequest.newInstance(
                listOf(
                    Field.ADDRESS_COMPONENTS,
                    Field.PHONE_NUMBER,
                    Field.WEBSITE_URI,
                    Field.OPENING_HOURS
                )
            )

            placesClient.findCurrentPlace(currentPlaceRequest).apply {
                addOnSuccessListener { response: FindCurrentPlaceResponse ->
                    try {
                        val result = response.placeLikelihoods
                            .map {
                                it.place
                            }
                            .map {
                                it.toString()
                            }
                            .map {
                                Place(it)
                            }

                        continuation.resume(result)
                    } catch (exception: Throwable) {
                        continuation.resumeWithException(exception)
                    }
                }
                addOnFailureListener { continuation.resumeWithException(it) }
            }
        } catch (exception: Throwable) {
            continuation.resumeWithException(exception)
        }
    }
}
