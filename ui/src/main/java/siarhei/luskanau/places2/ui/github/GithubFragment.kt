package siarhei.luskanau.places2.ui.github

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import siarhei.luskanau.places2.ui.R
import siarhei.luskanau.places2.ui.common.BaseFragment
import siarhei.luskanau.places2.ui.databinding.FragmentGithubBinding

@SuppressLint("ValidFragment")
class GithubFragment(
    presenterProvider: (args: Bundle?) -> Any
) : BaseFragment<Any>(presenterProvider) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentGithubBinding>(
                    inflater,
                    R.layout.fragment_github, container,
                    false
            ).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName
    }
}
