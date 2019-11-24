package siarhei.luskanau.places2.ui.placephotos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import siarhei.luskanau.places2.ui.R
import siarhei.luskanau.places2.ui.common.BaseFragment
import siarhei.luskanau.places2.ui.databinding.FragmentPlacePhotosBinding

@SuppressLint("ValidFragment")
class PlacePhotosFragment(
    presenterProvider: (args: Bundle?) -> PlacePhotosPresenter
) : BaseFragment<PlacePhotosPresenter>(presenterProvider) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentPlacePhotosBinding>(
                    inflater,
                    R.layout.fragment_place_photos,
                    container,
                    false
            ).also { binding ->
                binding.placePhotos.setOnClickListener { presenter.onPhotoClicked() }
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName
    }
}
