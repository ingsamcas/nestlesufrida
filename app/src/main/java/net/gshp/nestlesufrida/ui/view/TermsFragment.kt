package net.gshp.nestlesufrida.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.gshp.nestlesufrida.R
import net.gshp.nestlesufrida.databinding.FragmentTermsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TermsFragment : Fragment() {


    private lateinit var binding : FragmentTermsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentTermsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinueToLoginScreen.setOnClickListener {
            findNavController().navigate(R.id.action_TermsFragment_to_LoginFragment)
        }
    }

}