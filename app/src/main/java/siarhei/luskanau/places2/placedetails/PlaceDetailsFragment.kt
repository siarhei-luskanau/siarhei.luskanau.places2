package siarhei.luskanau.places2.placedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import siarhei.luskanau.places2.databinding.FragmentPlaceDetailsBinding

class PlaceDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlaceDetailsBinding.inflate(inflater, container, false)

        binding.placeDetails.setOnClickListener {
            val placeId = PlaceDetailsFragmentArgs.fromBundle(arguments ?: Bundle()).placeId
            val direction = PlaceDetailsFragmentDirections.actionPlaceDetailsToPhotos(placeId)
            it.findNavController().navigate(direction)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = javaClass.simpleName
    }
}