package siarhei.luskanau.places2.ui.placephotos

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

@SuppressLint("ValidFragment")
class PlacePhotosFragment(
    private val appNavigation: AppNavigation,
    private val placeId: String
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: siarhei.luskanau.places2.ui.databinding.FragmentPlacePhotosBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_place_photos, container, false)

        binding.placePhotos.setOnClickListener { appNavigation.goToPlaceList() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName
    }
}