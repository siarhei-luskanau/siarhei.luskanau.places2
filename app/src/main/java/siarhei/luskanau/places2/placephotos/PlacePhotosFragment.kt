package siarhei.luskanau.places2.placephotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import siarhei.luskanau.places2.databinding.FragmentPlacePhotosBinding

class PlacePhotosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlacePhotosBinding.inflate(inflater, container, false)

        PlacePhotosFragmentArgs.fromBundle(arguments ?: Bundle()).placeId

        return binding.root
    }
}