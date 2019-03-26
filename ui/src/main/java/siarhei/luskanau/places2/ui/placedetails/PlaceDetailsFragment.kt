package siarhei.luskanau.places2.ui.placedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import siarhei.luskanau.places2.ui.R
import siarhei.luskanau.places2.ui.common.BaseFragment
import siarhei.luskanau.places2.ui.databinding.FragmentPlaceDetailsBinding

@SuppressLint("ValidFragment")
class PlaceDetailsFragment(
    presenterProvider: (args: Bundle?) -> PlaceDetailsPresenter
) : BaseFragment<PlaceDetailsPresenter>(presenterProvider) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentPlaceDetailsBinding>(
                    inflater,
                    R.layout.fragment_place_details,
                    container,
                    false
            ).also { binding ->
                binding.placeDetails.setOnClickListener { presenter.onPhotosClicked() }
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName
    }
}