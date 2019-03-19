package siarhei.luskanau.places2.domain

import android.os.Bundle

interface AppNavigationArgs {

    fun getPlaceDetailsFragmentArgs(args: Bundle?): String
    fun getPlacePhotosFragmentArgs(args: Bundle?): String
}