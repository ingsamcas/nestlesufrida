package net.gshp.nestlesufrida.ui.view

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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import net.gshp.nestlesufrida.BuildConfig
import net.gshp.nestlesufrida.R
import net.gshp.nestlesufrida.databinding.FragmentLoginBinding
import net.gshp.nestlesufrida.ui.viewmodel.LoginViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment() : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var supportNumber: String

    private val loginViewModel : LoginViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                makeCall()
            } else {
                Toast.makeText(context, getString(R.string.permissions_request_required), Toast.LENGTH_LONG).show()
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportNumber = getString(R.string.support_number)
        initUI();
        initListeners(view)
        observeChanges()
    }

    private fun observeChanges(){
        loginViewModel.loginModel.observe(viewLifecycleOwner, Observer { token ->
            Log.i("LoginFragment", token)
        })
    }

    private fun initUI() {
        val version = "v. ${BuildConfig.VERSION_NAME}"
        binding.tvVersion.text = version
    }

    private fun initListeners(view: View) {
        binding.tvTerms.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_TermsFragment)
        }

        binding.buttonLogin.setOnClickListener() {

            val etUser = binding.etUser.editText?.text?.toString() ?: ""
            val etPass = binding.etPass.editText?.text?.toString() ?: ""

            loginViewModel.login("", "")

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
                requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
            }
        }
    }

}