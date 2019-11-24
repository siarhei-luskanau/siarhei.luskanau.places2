package siarhei.luskanau.places2.ui.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import siarhei.luskanau.places2.ui.R
import siarhei.luskanau.places2.ui.common.BaseFragment
import siarhei.luskanau.places2.ui.databinding.FragmentPermissionsBinding

private const val PERMISSIONS_REQUEST_CODE = 100
private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

class PermissionsFragment(
    presenterProvider: (args: Bundle?) -> PermissionsPresenter
) : BaseFragment<PermissionsPresenter>(presenterProvider) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<FragmentPermissionsBinding>(
            inflater,
            R.layout.fragment_permissions, container,
            false
        ).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = javaClass.simpleName

        if (hasPermissions().not()) {
            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
        } else {
            // If permissions have already been granted, proceed
            presenter.onPermissionsGranted()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Take the user to the success fragment when permission is granted
                Toast.makeText(context, "Permission request granted", Toast.LENGTH_LONG).show()
                presenter.onPermissionsGranted()
            } else {
                Toast.makeText(context, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun hasPermissions(): Boolean {
        for (permission in PERMISSIONS_REQUIRED) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }
}
