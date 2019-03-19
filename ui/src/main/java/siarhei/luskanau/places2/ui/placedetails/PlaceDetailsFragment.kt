package siarhei.luskanau.places2.ui.placedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import siarhei.luskanau.places2.domain.AppNavigation
import siarhei.luskanau.places2.ui.R
import siarhei.luskanau.places2.ui.databinding.FragmentPlaceDetailsBinding

@SuppressLint("ValidFragment")
class PlaceDetailsFragment(
    private val appNavigation: AppNavigation,
    private val placeId: String
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPlaceDetailsBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_place_details, container, false)

        binding.placeDetails.setOnClickListener { appNavigation.goToPlacePhotos(placeId) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName
    }
}