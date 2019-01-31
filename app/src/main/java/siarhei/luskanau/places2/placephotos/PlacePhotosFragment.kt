package siarhei.luskanau.places2.placephotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import siarhei.luskanau.places2.databinding.FragmentPlacePhotosBinding

class PlacePhotosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlacePhotosBinding.inflate(inflater, container, false)

        PlacePhotosFragmentArgs.fromBundle(arguments ?: Bundle()).placeId

        binding.placePhotos.setOnClickListener {
            val direction = PlacePhotosFragmentDirections.actionPlaceDetailsToPlaceList()
            it.findNavController().navigate(direction)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = javaClass.simpleName
    }
}