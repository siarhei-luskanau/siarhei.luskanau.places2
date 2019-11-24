package siarhei.luskanau.places2.ui.placelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import siarhei.luskanau.places2.ui.R
import siarhei.luskanau.places2.ui.common.BaseFragment
import siarhei.luskanau.places2.ui.databinding.FragmentPlaceListBinding
import siarhei.luskanau.places2.ui.databinding.ViewPlaceListEmptyBinding
import siarhei.luskanau.places2.ui.databinding.ViewPlaceListErrorBinding
import siarhei.luskanau.places2.ui.databinding.ViewPlaceListNormalBinding
import siarhei.luskanau.places2.ui.databinding.ViewPlaceListPermissionBinding

@SuppressLint("ValidFragment", "SetTextI18n")
class PlaceListFragment(
    presenterProvider: (args: Bundle?) -> PlaceListPresenter
) : BaseFragment<PlaceListPresenter>(presenterProvider) {

    private lateinit var fragmentBinding: FragmentPlaceListBinding
    private lateinit var emptyStateBinding: ViewPlaceListEmptyBinding
    private lateinit var errorStateBinding: ViewPlaceListErrorBinding
    private lateinit var normalStateBinding: ViewPlaceListNormalBinding
    private lateinit var permissionStateBinding: ViewPlaceListPermissionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DataBindingUtil.inflate<FragmentPlaceListBinding>(
            inflater,
            R.layout.fragment_place_list,
            container,
            false
        ).also {
            fragmentBinding = it
            fragmentBinding.placeList.setOnClickListener { presenter.onPlaceClicked("placeId") }
        }

        emptyStateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.view_place_list_empty,
            container,
            false
        )

        errorStateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.view_place_list_error,
            container,
            false
        )

        DataBindingUtil.inflate<ViewPlaceListNormalBinding>(
            inflater,
            R.layout.view_place_list_normal,
            container,
            false
        ).also {
            normalStateBinding = it
        }

        permissionStateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.view_place_list_permission,
            container,
            false
        )

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName
        presenter.requestPlaceList()
    }

    override fun observeDataSources() {
        super.observeDataSources()
        presenter.getStateData().observe(viewLifecycleOwner, Observer { changeState(it) })
    }

    private fun changeState(state: PlaceListState) {
        val stateView = when (state) {
            is NormalState -> normalStateBinding
            is EmptyState -> emptyStateBinding
            is ErrorState -> errorStateBinding
            is PermissionState -> permissionStateBinding
        }

        if (fragmentBinding.containerContent.getChildAt(0) != stateView) {
            fragmentBinding.containerContent.removeAllViews()
            fragmentBinding.containerContent.addView(stateView.root)
        }
    }
}
