package siarhei.luskanau.places2.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceUI(
    val id: String,
    var name: CharSequence? = null,
    var address: CharSequence? = null,
    var phoneNumber: CharSequence? = null,
    var websiteUri: String? = null,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var photos: List<PhotoUI>? = null
) : Parcelable