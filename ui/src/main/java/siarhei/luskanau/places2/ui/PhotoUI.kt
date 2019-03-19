package siarhei.luskanau.places2.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoUI(
    var photoUrl: String
) : Parcelable