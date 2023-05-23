package net.gshp.nestlesufrida

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import net.gshp.nestlesufrida.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var supportNumber: String

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permisssion", "Permiso aceptado")
                makeCall()
            } else {
                Log.i("Permisssion", "Permiso rechazado")
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportNumber = getString(R.string.support_number)
        initUI();
        initListeners(view)
    }

    private fun initUI() {
        val version = "v. ${BuildConfig.VERSION_NAME}"
        binding.tvVersion.text = version
    }

    private fun initListeners(view: View) {
        binding.tvTerms.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonLogin.setOnClickListener() {

            val etUser = binding.etUser.text.toString()
            val etPass = binding.etPass.text.toString()

            Snackbar.make(
                view,
                if (etUser.isEmpty() || etPass.isEmpty()) "Rellena ambos campos" else "User = $etUser - Pass = $etPass",
                Snackbar.LENGTH_LONG
            )
                .setAction("Action", null).show()
        }

        binding.tvSupport.setOnClickListener() {
            showDialog(view)
        }

    }


    private fun showDialog(view: View) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_support)

        val btnSupportYes: Button = dialog.findViewById(R.id.btnSupportYes)
        val btnSupportNo: Button = dialog.findViewById(R.id.btnSupportNo)
        val tvCommunicate: TextView = dialog.findViewById(R.id.tvCommunicate)

        val communicateNumberMessage : String = getString(R.string.support_comunicate)
        tvCommunicate.text = "$communicateNumberMessage $supportNumber"

        btnSupportYes.setOnClickListener {
            checkPermission(view)
        }

        btnSupportNo.setOnClickListener {
            dialog.hide()
        }

        dialog.show()
    }

    private fun makeCall() {
        val dialIntent = Intent(Intent.ACTION_CALL)
        dialIntent.data = Uri.parse("tel:$supportNumber")
        startActivity(dialIntent)
    }

    private fun checkPermission(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                view.context,
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED -> {
                makeCall()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                view.context as Activity,
                android.Manifest.permission.CALL_PHONE
            ) -> {
                requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
            }
            else -> {
                //Permiso no ha sido pedido
                requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}