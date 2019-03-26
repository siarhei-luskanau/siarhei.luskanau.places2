package siarhei.luskanau.places2.ui.placelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    private val errorStateView by lazy {
        TextView(requireContext()).apply { text = "errorStateView" }
    }
    private val emptyStateView by lazy {
        TextView(requireContext()).apply { text = "emptyStateView" }
    }
    private val normalStateView by lazy {
        TextView(requireContext()).apply { text = "normalStateView" }
    }

    private lateinit var binding: FragmentPlaceListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
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
        val stateView = when (state) {
            is NormalState -> normalStateView
            is EmptyState -> emptyStateView
            is ErrorState -> errorStateView
        }

        if (binding.containerContent.getChildAt(0) != stateView) {
            binding.containerContent.removeAllViews()
            binding.containerContent.addView(stateView)
        }
    }
}