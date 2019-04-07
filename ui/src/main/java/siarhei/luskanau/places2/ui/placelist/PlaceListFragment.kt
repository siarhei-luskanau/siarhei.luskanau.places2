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

@SuppressLint("ValidFragment", "SetTextI18n")
class PlaceListFragment constructor(
    presenterProvider: (args: Bundle?) -> PlaceListPresenter
) : BaseFragment<PlaceListPresenter>(presenterProvider) {

    private lateinit var binding: FragmentPlaceListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<FragmentPlaceListBinding>(
            inflater,
            R.layout.fragment_place_list,
            container,
            false
        ).also {
            binding = it
            binding.placeList.setOnClickListener { presenter.onPlaceClicked("placeId") }
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName
        presenter.requestPlaceList()
    }

    override fun observeDataSources() {
        super.observeDataSources()
        presenter.stateData.observe(viewLifecycleOwner, Observer { changeState(it) })
    }

    private fun changeState(state: PlaceListState) {
        when (state) {
            is NormalState -> binding.placeListContent.text = "normalStateView"
            is EmptyState -> binding.placeListContent.text = "emptyStateView"
            is ErrorState -> binding.placeListContent.text = "errorStateView"
        }
    }
}